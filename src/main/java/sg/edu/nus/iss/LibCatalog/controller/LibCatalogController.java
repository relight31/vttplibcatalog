package sg.edu.nus.iss.LibCatalog.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import sg.edu.nus.iss.LibCatalog.model.Book;
import sg.edu.nus.iss.LibCatalog.service.LibService;

@Controller
public class LibCatalogController {
    private Logger logger = Logger.getLogger(LibCatalogController.class.getName());

    @Autowired
    LibService service;

    /*
     * TODO
     * functionalies:
     * query search title and author
     * directory of books getAll
     * sort by title or author
     * pagination
     * change num of books in a search
     */

    @GetMapping("/")
    public String indexPage(Model model) {
        logger.log(Level.INFO, "Show landing page");
        model.addAttribute("helloWorld", "Hello World");
        model.addAttribute("imgLink", "library.png");
        return "search";
    }

    @GetMapping("/load")
    public String loadDB(Model model) {
        int booklistLength = service.loadBooks();
        model.addAttribute("booklistLength", booklistLength);
        return "loaded";
    }

    // http://localhost:8080/searchTitle?searchTerm=&searchType=author&sortType=forward

    @GetMapping("/search")
    public String searchResults(Model model, @RequestParam String searchTerm, @RequestParam String searchType,
            @RequestParam String sortType) {
        logger.log(Level.INFO, searchTerm + searchType + sortType);
        List<Book> result = new ArrayList<Book>();
        logger.log(Level.INFO, "result list instantiated");
        if (searchType.equals("author")) {
            logger.log(Level.INFO, "able to parse search type");
            result = service
                    .sortByAuthor(searchTerm, sortType.equals("forward"));
        } else {
            result = service
                    .sortByTitle(searchTerm, sortType.equals("forward"));
        }
        logger.log(Level.INFO, "result list populated with " + result.size() + " records");
        for (Book book : result) {
            logger.log(Level.INFO, book.getAuthor());
        }
        model.addAttribute("books", result);
        return "searchResults";
    }

    @GetMapping("/findById")
    public String findById(Model model, @RequestParam String bookId) {
        Book result = service.findById(bookId);
        model.addAttribute("book", result);
        return "findById";
    }
}
