package sg.edu.nus.iss.LibCatalog.service;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
    @Qualifier("library") // to use named bean, don't use unless necessary
    RedisTemplate<String, Object> redisTemplate;

    public void saveBooks(List<Book> bookList) {
        for (Book book : bookList) {
            redisTemplate.opsForHash().put(bookMap, book.getId(), book);
        }
        logger.log(Level.INFO, "Successfully loaded " + bookList.size() + " book records");
    }

    public Book findById(String bookId) {
        Book book = (Book) redisTemplate.opsForHash().get(bookMap, bookId);
        logger.log(Level.INFO, "Successfully retrieved book: " + bookId);
        return book;
    }

    public List<Book> getAllBooks() {
        logger.log(Level.INFO, "Calling getAllBooks method");
        List<Book> books = (List<Book>) redisTemplate.opsForHash()
                .values(bookMap)
                .stream() // stack of stream-filter-map does marshalling of data stream into objects
                .filter(Book.class::isInstance)
                .map(Book.class::cast)
                .toList();
        logger.log(Level.INFO, "Successfully retrieved " + books.size() + " books as List");
        return books;
    }

}
