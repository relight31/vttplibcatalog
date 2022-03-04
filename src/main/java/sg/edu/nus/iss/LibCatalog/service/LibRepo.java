package sg.edu.nus.iss.LibCatalog.service;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import sg.edu.nus.iss.LibCatalog.model.Book;

// interact with the Redis DB
@Repository
public class LibRepo {
    // setup repo
    private Logger logger = Logger.getLogger(LibRepo.class.getName());
    private static final String bookMap = "bookMap";

    @Autowired
    RedisTemplate<String, Object> redisTemplate;

    public Book findById(String bookId) {
        Book result = (Book) redisTemplate.opsForHash().get(bookMap, bookId);
        logger.log(Level.INFO, "Successfully retrieved book: " + bookId);
        return result;
    }

    /*
     * TODO
     * public List<Book> getAllBooks(int startIndex, int numPages){
     * 
     * }
     */
}
