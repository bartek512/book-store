package book.book.mapper.impl;

import book.book.entity.Book;
import book.book.mapper.BookMapper;
import book.book.to.BookTo;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class BookMapperImpl implements BookMapper {

    @Override
    public BookTo map2To(final Book book) {
        if (book != null) {
            return new BookTo(book.getId(), book.getTitle(), new HashSet<>(book.getAuthors()), book.getDescription(), book.getStatus(),
                    book.getCategories());
        }
        return null;
    }

    @Override
    public Book map2Entity(final BookTo bookTo) {
        if (bookTo != null) {
            return new Book(bookTo.getId(), bookTo.getTitle(), new HashSet<>(bookTo.getAuthors()), bookTo.getDescription(), bookTo.getStatus(),
                    bookTo.getCategories());
        }
        return null;
    }

    @Override
    public List<BookTo> map2To(final List<Book> bookEntities) {
        return bookEntities.stream()
                .map(this::map2To)
                .collect(Collectors.toList());
    }

    @Override
    public List<Book> map2Entity(final List<BookTo> bookEntities) {
        return bookEntities.stream()
                .map(this::map2Entity)
                .collect(Collectors.toList());
    }

}
