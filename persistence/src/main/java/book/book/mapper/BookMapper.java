package book.book.mapper;

import book.book.entity.Book;
import book.book.to.BookTo;

import java.util.List;

public interface BookMapper {

	BookTo map2To(Book book);

	Book map2Entity(BookTo bookTo);

	List<BookTo> map2To(List<Book> bookEntities);

	List<Book> map2Entity(List<BookTo> bookEntities);

}
