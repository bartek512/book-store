package book.book.repository;

import book.book.entity.Book;
import book.book.enumerations.BookCategory;
import book.book.enumerations.BookStatus;
import book.book.to.BookTo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.stream.Collectors;

public interface BookRepository extends JpaRepository<Book, Long> {

    default List<Book> findBookByTitle(final String title) {
        return this.findAll()
                .stream()
                .filter(b -> b.getTitle()
                        .contains(title))
                .collect(Collectors.toList());
    }

    default List<Book> findBookByCategory(final BookCategory category) {
        return this.findAll()
                .stream()
                .filter(b -> b.getCategories()
                        .contains(category))
                .collect(Collectors.toList());
    }

    default List<Book> findBookByStatus(final BookStatus status) {
        return this.findAll()
                .stream()
                .filter(b -> b.getStatus()
                        .equals(status))
                .collect(Collectors.toList());
    }

    default List<BookTo> findBookByStatus(final BookStatus status, List<BookTo> books) {
        return books
                .stream()
                .filter(b -> b.getStatus()
                        .equals(status))
                .collect(Collectors.toList());
    }

}
