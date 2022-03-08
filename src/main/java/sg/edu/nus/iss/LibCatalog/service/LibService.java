package sg.edu.nus.iss.LibCatalog.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sg.edu.nus.iss.LibCatalog.model.Book;

// run all business logic here
@Service
public class LibService {
    Logger logger = Logger.getLogger(LibService.class.getName());
    @Autowired
    LibRepo libRepo;

    public Book findById(String bookId) { // working
        logger.log(Level.INFO, "Calling libRepo.findById(" + bookId + ")");
        return libRepo.findById(bookId);
    }

    public List<Book> findByAuthor(String author) {
        List<Book> result = libRepo.getAllBooks();
        result = result.stream()
                .filter(book -> book.getAuthor().contains(author))
                .toList();
        return result;
    }

    public List<Book> findByTitle(String title) {
        List<Book> result = libRepo.getAllBooks();
        result = result.stream()
                .filter(book -> book.getTitle().contains(title))
                .toList();
        return result;
    }

    public List<Book> sortByAuthor(String author, boolean forward) {
        logger.log(Level.INFO, "able to call method");
        List<Book> result = findByAuthor(author);
        logger.log(Level.INFO, "able to retrieve unsorted list");
/*         if (forward) {
            // TODO fix sort method
            Collections.sort(result, new AuthorComparator());
        } else {
            Collections.sort(result, new AuthorComparator().reversed());
        } */
        return result;
    }

    public List<Book> sortByTitle(String title, boolean forward) {
        List<Book> result = findByTitle(title);
        if (forward) {
            Collections.sort(result, new TitleComparator());
        } else {
            Collections.sort(result, new TitleComparator().reversed());
        }
        return result;
    }

    public int loadBooks() {
        List<Book> booklist = new ArrayList<Book>();

        Book book1 = new Book("Daisy Meadows",
                "Ella the Rose Fairy",
                "/thumbnails/ella_the_rose_fairy.jpg");
        Book book2 = new Book("JK Rowling",
                "Harry Potter and the Philosopher's Stone",
                "/thumbnails/harry-potter-p-stone.jpg");
        Book book3 = new Book("Savannah Leigh",
                "The Haunted Tower",
                "thumbnails/the_haunted_tower.jpg");
        Book book4 = new Book();
        Book book5 = new Book("James Clear",
                "Atomic Habits: An Easy & Proven Way to Build Good Habits & Break Bad Ones",
                "thumbnails/atomic-habits.jpg");
        Book book6 = new Book("Nikhil Abraham",
                "Coding All-in-One For Dummies",
                "thumbnails/coding-for-dummies.jpg");
        Book book7 = new Book("Mattson Tomlin",
                "Batman: The Imposter",
                "thumbnails/batman-the-imposter.jpg");
        Book book8 = new Book("Gege Akutami",
                "Jujutsu Kaisen, Vol. 1",
                "thumbnails/jujutsu-kaisen.jpg");
        Book book9 = new Book("Chad Zunker",
                "Family Money",
                "thumbnails/family-money.jpg");
        Book book10 = new Book("Eric Carle",
                "The Very Hungry Caterpillar");
        booklist.add(book1);
        booklist.add(book2);
        booklist.add(book3);
        booklist.add(book4);
        booklist.add(book5);
        booklist.add(book6);
        booklist.add(book7);
        booklist.add(book8);
        booklist.add(book9);
        booklist.add(book10);

        libRepo.saveBooks(booklist);
        return booklist.size();
    }
}
