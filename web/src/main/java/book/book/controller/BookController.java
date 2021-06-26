package book.book.controller;

import book.book.constants.ViewNames;
import book.book.service.impl.BookServiceImpl;
import book.book.to.BookTo;
import book.book.to.SearchBookTo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class BookController {

    BookServiceImpl bookService;

    @Autowired
    public BookController(BookServiceImpl bookService) {
        this.bookService = bookService;
    }

    /**
     * Returns all books
     *
     * @return list of all books
     */
    @GetMapping("/books")
    public String getAllBooks(Model model) {

        model.addAttribute("bookList", bookService.findAllBooks());
        return ViewNames.BOOKS_PAGE;
    }

    /**
     * Returns books with specified params. It is possible to filter by all params and using one or two.
     *
     * @param book object with specified data
     * @return List of books mmatching to specified data
     */
    @RequestMapping("/filter")
    public String getAllByBooks(@ModelAttribute SearchBookTo book, Model model) {

        if (book.getTitle().isEmpty()) {
            book.setTitle(null);
        }
        List<BookTo> bookListFilteredByStatus = new ArrayList<>();
        bookListFilteredByStatus = bookService.filterBooks(book);
        model.addAttribute("bookList", bookListFilteredByStatus);

        return ViewNames.BOOKS_PAGE;
    }

    /**
     * Show details about specified book
     *
     * @param id of book
     * @return book with all data
     */
    @GetMapping("/books/book")
    public String getBookDetails(@RequestParam("id") Long id, Model model) {

        model.addAttribute("book", bookService.findById(id));
        model.addAttribute("book.description", bookService.findById(id).getDescription());
        model.addAttribute("book.category", bookService.findById(id).getCategories());
        return ViewNames.BOOK;
    }

    /**
     * Redirect to adding book form
     *
     * @return added book
     */
    @GetMapping("/books/add")
    public String getAddBookPage(Model model) {
        model.addAttribute("newBook", new BookTo());
        return ViewNames.ADD_BOOK;
    }

    /**
     * Adds book to database
     *
     * @return added book
     */
    @PostMapping("/add")
    public String addBook(@ModelAttribute("newBook") BookTo newBook, Model model) {
        model.addAttribute("newBook", newBook);
        bookService.saveBook(newBook);
        return ViewNames.BOOKS_PAGE;
    }

    /**
     * Redirect to page with information about removing book
     */
    @GetMapping("/removeBook")
    public String getRemoveBookPage() {
        return "removeBook";
    }

    /**
     * Removes specified book
     *
     * @param id id of book
     */
    @GetMapping("/books/remove/{id}")
    public String removeBook(@PathVariable("id") Long id, Model model) {

        BookTo book = bookService.findById(id);
        bookService.deleteBook(book.getId());
        return ViewNames.REMOVE_BOOK;
    }
}
