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
        return "search";
    }

    // http://localhost:8080/searchTitle?searchTerm=&searchType=author&sortType=forward

    @GetMapping("/search")
    public String searchResults(Model model, @RequestParam String searchTerm, @RequestParam String searchType,
            @RequestParam String sortType) {
        List<Book> result = new ArrayList<Book>();

        if (searchType.equals("author")) {
            result = service
                    .sortByAuthor(searchTerm, sortType.equals("forward"));
        } else {
            result = service
                    .sortByTitle(searchTerm, sortType.equals("forward"));
        }

        model.addAttribute("books", result);
        return "searchResults";
    }
}
