package book.book.rest.controller;


import book.book.exception.BusinessException;
import book.book.service.BookService;
import book.book.to.BookTo;
import book.book.to.SearchBookTo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest")
public class BookRestController {

    private final BookService bookService;


    @Autowired
    public BookRestController(BookService bookService) {
        this.bookService = bookService;
    }

    /**
     * Returns all books
     *
     * @return list of all books
     */
    @GetMapping("/books")
    public ResponseEntity<List<BookTo>> getAllBooks() {

        List<BookTo> booksTo = bookService.findAllBooks();
        return ResponseEntity.ok().body(booksTo);
    }

    /**
     * Show details about specified book
     *
     * @param id of book
     * @return book with all data
     */
    @GetMapping("/books/{id}")
    public ResponseEntity<BookTo> findBook(@PathVariable("id") final Long id) {

        final BookTo book = this.bookService.findById(id);
        return ResponseEntity.ok()
                .body(book);
    }

    /**
     * Adds book to database
     *
     * @return added book
     */
    @PostMapping(value = "/add")
    public ResponseEntity<BookTo> createBook(@RequestBody BookTo book) {
        if (book.getTitle() == null) {
            throw new BusinessException("Book must have a title");
        }
        BookTo bookTo = bookService.saveBook(book);
        return ResponseEntity.ok().body(bookTo);
    }

    /**
     * Gets book by title
     *
     * @param name title of book
     * @return book with specified title
     */
    @GetMapping("/booksByTitle/{title}")
    public ResponseEntity<List<BookTo>> findBooksByTitle(@PathVariable("title") final String name) {

        List<BookTo> listOfBooks = this.bookService.findBooksByTitle(name);
        return ResponseEntity.ok()
                .body(listOfBooks);
    }


    /**
     * Returns books with specified params. It is possible to filter by all params and using one or two.
     *
     * @param book object with specified data
     * @return List of books mmatching to specified data
     */
    @PostMapping("/filter")
    public ResponseEntity<List<BookTo>> filterBook(@RequestBody SearchBookTo book) {

        List<BookTo> filteredBooks = bookService.filterBooks(book);

        return ResponseEntity.ok()
                .body(filteredBooks);
    }
}
