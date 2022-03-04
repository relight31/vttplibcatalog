package sg.edu.nus.iss.LibCatalog.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.edu.nus.iss.LibCatalog.model.Book;

// run all business logic here
@Service
public class LibService {
    @Autowired
    LibRepo libRepo;

    public Book findById(String bookId) {
        return libRepo.findById(bookId);
    }

    public List<Book> findByAuthor(String author) {
        List<Book> result = libRepo.getAllBooks();
        return result;
    }

    public List<Book> findByTitle(String title) {
        List<Book> result = new ArrayList<Book>();
        return result;
    }

    public List<Book> sortByAuthor(String author, boolean forward) {
        // TODO
        List<Book> result = libRepo.getAllBooks();
        if (forward) {
            Collections.sort(result, new AuthorComparator());
        } else {
            Collections.sort(result, new AuthorComparator().reversed());
        }
        return result;
    }

    public List<Book> sortByTitle(String title, boolean forward) {
        // TODO
        List<Book> result = new ArrayList<Book>();
        if (forward) {
            Collections.sort(result, new TitleComparator());
        } else {
            Collections.sort(result, new TitleComparator().reversed());
        }
        return result;
    }
}
