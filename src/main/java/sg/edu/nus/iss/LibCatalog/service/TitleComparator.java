package sg.edu.nus.iss.LibCatalog.service;

import java.io.Serializable;
import java.util.Comparator;

import sg.edu.nus.iss.LibCatalog.model.Book;

public class TitleComparator implements Comparator<Book>, Serializable {
    @Override
    public int compare(Book book1, Book book2) {
        return book1.getTitle().compareToIgnoreCase(book2.getTitle());
    }
}
