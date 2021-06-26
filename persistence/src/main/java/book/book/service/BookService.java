package book.book.service;

import book.book.enumerations.BookCategory;
import book.book.enumerations.BookStatus;
import book.book.to.BookTo;
import book.book.to.SearchBookTo;

import java.util.List;
import java.util.Set;

public interface BookService {

    List<BookTo> findAllBooks();

    BookTo findById(Long id);

    List<BookTo> findBooksByTitle(String title);

    BookTo saveBook(BookTo book);

    void deleteBook(Long id);

    List<BookTo> findByCategory(Set<BookCategory> category, List<BookTo> bookList);

    List<BookTo> findByCategory(Set<BookCategory> category);

    List<BookTo> findByStatus(BookStatus status);

    List<BookTo> findByStatus(BookStatus status, List<BookTo> books);

    List<BookTo> filterBooks(SearchBookTo book);
}
