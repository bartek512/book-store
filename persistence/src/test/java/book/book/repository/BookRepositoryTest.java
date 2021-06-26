package book.book.repository;

import book.book.config.PersistenceTestConfig;
import book.book.entity.Book;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = PersistenceTestConfig.class)
public class BookRepositoryTest {

	@Autowired
	private BookRepository bookRepository;

	@Test
	public void testShouldFindBookById() {
		// given
		final long bookId = 1;
		// when
		final Optional<Book> bookEntity = this.bookRepository.findById(bookId);
		// then
		assertNotNull(bookEntity);
		assertTrue(bookEntity.isPresent());
		assertEquals("Java dla początkujących", bookEntity.get()
				.getTitle());
	}

	@Test
	public void testShouldFindBooksByTitle() {
		// given
		final String bookTitle = "J";
		// when
		final List<Book> booksEntity = this.bookRepository.findBookByTitle(bookTitle);
		// then
		assertNotNull(booksEntity);
		assertFalse(booksEntity.isEmpty());
		assertEquals("Java dla początkujących", booksEntity.get(0)
				.getTitle());
	}

}
