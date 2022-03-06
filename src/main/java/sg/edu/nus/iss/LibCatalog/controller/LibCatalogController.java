package sg.edu.nus.iss.LibCatalog.controller;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LibCatalogController {
    private Logger logger = Logger.getLogger(LibCatalogController.class.getName());
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
        return "index";
    }
}
