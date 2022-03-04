package sg.edu.nus.iss.LibCatalog.service;

import java.util.Comparator;

import sg.edu.nus.iss.LibCatalog.model.Book;

public class AuthorComparator implements Comparator<Book>{
    public int compare(Book book1, Book book2){
        return book1.getAuthor().compareToIgnoreCase(book2.getAuthor());
    }
}
