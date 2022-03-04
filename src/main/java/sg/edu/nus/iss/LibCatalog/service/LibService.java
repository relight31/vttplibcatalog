package sg.edu.nus.iss.LibCatalog.service;

import java.util.ArrayList;
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
        List<Book> result = new ArrayList<Book>();
        return result;
    }

    public List<Book> findByTitle(String title) {
        List<Book> result = new ArrayList<Book>();
        return result;
    }

    public List<Book> sortByAuthor(String author, boolean forward) {
        List<Book> result = new ArrayList<Book>();
        return result;
    }

    public List<Book> sortByTitle(String title, boolean forward) {
        List<Book> result = new ArrayList<Book>();
        return result;
    }
}
