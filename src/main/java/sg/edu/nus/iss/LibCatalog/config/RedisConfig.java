package sg.edu.nus.iss.LibCatalog.config;

import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {
    private Logger logger = Logger.getLogger(RedisConfig.class.getName());

    @Value("${spring.redis.host}")
    private String redisHost;

    @Value("${spring.redis.port}")
    private Optional<Integer> redisPort;

    // @Value("${spring.redis.password}")
    // private String redisPassword;

    @Bean(name = "library") // Beans have no constructors with parameters
    // @Bean (put name only if necessary, name is hazardous if typo)
    @Scope("singleton")
    public RedisTemplate<String, Object> redisTemplate() {
        // this a factory method, produces an object to be injected
        // RedisConfiguration talks to Jedis driver which talks to Redis
        logger.log(Level.INFO, "Creating new config");
        final RedisStandaloneConfiguration config = new RedisStandaloneConfiguration();

        logger.log(Level.INFO, "Host: " + redisHost + " Port: " + redisPort);
        config.setHostName(redisHost);
        config.setPort(redisPort.get());
        // config.setPassword(redisPassword);
        logger.log(Level.INFO, "Successfully set Host " + redisHost + " and Port: " + redisPort);

        // config and connect config to Jedis driver
        final JedisClientConfiguration jedisClient = JedisClientConfiguration.builder().build();
        final JedisConnectionFactory jedisFac = new JedisConnectionFactory(config, jedisClient);
        jedisFac.afterPropertiesSet();

        // allow app to communicate with RedisTemplate
        RedisTemplate<String, Object> template = new RedisTemplate<String, Object>();
        template.setConnectionFactory(jedisFac);

        // convert Strings to UTF-8
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new StringRedisSerializer());
        template.setHashKeySerializer(new StringRedisSerializer());
        template.setHashValueSerializer(new StringRedisSerializer());

        RedisSerializer<Object> serializer = new JdkSerializationRedisSerializer(getClass().getClassLoader());

        template.setValueSerializer(serializer);

        return template;
    }
}
