package book.book.service.impl;

import book.book.entity.Book;
import book.book.enumerations.BookCategory;
import book.book.enumerations.BookStatus;
import book.book.exception.BusinessException;
import book.book.mapper.BookMapper;
import book.book.repository.BookRepository;
import book.book.to.BookTo;
import book.book.to.SearchBookTo;
import book.book.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    private final BookMapper bookMapper;

    @Autowired
    public BookServiceImpl(final BookRepository bookRepository, final BookMapper bookMapper) {
        this.bookRepository = bookRepository;
        this.bookMapper = bookMapper;
    }

    /**
     * returns lit of all books
     *
     * @return
     */
    @Override
    public List<BookTo> findAllBooks() {
        return this.bookMapper.map2To(this.bookRepository.findAll());
    }

    /**
     * Returns book with specified if
     *
     * @param id of book
     * @return
     */
    @Override
    public BookTo findById(final Long id) {
        final Optional<Book> book = this.bookRepository.findById(id);
        if (!book.isPresent()) {
            throw new BusinessException("Couldn't find book with ID" + id);
        }
        return this.bookMapper.map2To(book.get());
    }

    /**
     * Returns list of books with specified title
     *
     * @param title of book
     * @return
     */
    @Override
    public List<BookTo> findBooksByTitle(final String title) {
        return findAllBooks().stream()
                .filter(bookTo -> bookTo
                        .getTitle()
                        .equals(title))
                .collect(Collectors.toList());
    }

    /**
     * Saves entity in database
     *
     * @param book saved book
     * @return
     */
    @Override
    @Transactional
    public BookTo saveBook(final BookTo book) {

        Book entity = this.bookMapper.map2Entity(book);
        entity = bookRepository.save(entity);
        return this.bookMapper.map2To(entity);
    }

    /**
     * Delete book from dataase with specified id
     *
     * @param id of book to delete
     */
    @Override
    @Transactional
    public void deleteBook(final Long id) {
        this.bookRepository.deleteById(id);
    }

    /**
     * Returns all books with specified category
     *
     * @param category to find
     * @return
     */
    @Override
    public List<BookTo> findByCategory(Set<BookCategory> category) {

        return findAllBooks().stream()
                .filter(bookTo -> bookTo
                        .getCategories()
                        .containsAll(category))
                .collect(Collectors.toList());
    }

    /**
     * Returns all books with specified category from list
     *
     * @param category to find
     * @param bookList list to filter
     * @return
     */
    @Override
    public List<BookTo> findByCategory(Set<BookCategory> category, List<BookTo> bookList) {

        return bookList.stream()
                .filter(bookTo -> bookTo
                        .getCategories()
                        .containsAll(category))
                .collect(Collectors.toList());
    }

    /**
     * Returns all books with specified status
     *
     * @param status of books to returns
     * @return
     */
    @Override
    public List<BookTo> findByStatus(BookStatus status) {

        return findAllBooks().stream()
                .filter(bookTo -> bookTo
                        .getStatus().name()
                        .equals(status.name()))
                .collect(Collectors.toList());
    }

    /**
     * Returns all books with specified status from list
     *
     * @param status of books to returns
     * @param books  list to filter
     * @return
     */
    @Override
    public List<BookTo> findByStatus(BookStatus status, List<BookTo> books) {

        return books.stream()
                .filter(bookTo -> bookTo
                        .getStatus()
                        .equals(status))
                .collect(Collectors.toList());
    }

    /**
     * Filter books with specified data
     *
     * @param book object with data to filter books in db
     * @return list o filtered books
     */
    @Override
    public List<BookTo> filterBooks(SearchBookTo book) {

        List<BookTo> bookListReadyToReturn = findAllBooks();

        if (book.getTitle() == null && book.getStatus() == null && book.getCategories() == null) {
            return bookListReadyToReturn;
        } else {

            if ((book.getTitle() != null)) {
                bookListReadyToReturn = findBooksByTitle(book.getTitle());
            }

            if (book.getCategories() != null) {
                bookListReadyToReturn = findByCategory(book.getCategories(), bookListReadyToReturn);
            }

            if (book.getStatus() != null) {
                bookListReadyToReturn = findByStatus(book.getStatus(), bookListReadyToReturn);
            }

            return bookListReadyToReturn;
        }
    }
}
