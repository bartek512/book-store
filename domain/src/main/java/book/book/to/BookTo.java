package book.book.to;

import book.book.enumerations.BookCategory;
import book.book.enumerations.BookStatus;

import java.util.Set;

public class BookTo {

	private Long id;

	private String title;

	private Set<String> authors;

	private String description;

	private BookStatus status;

	private Set<BookCategory> categories;

	public BookTo() {
	}


	public BookTo(final Long id, final String title, final Set<String> authors, final String description, final BookStatus status,
			final Set<BookCategory> categories) {
		this.id = id;
		this.title = title;
		this.authors = authors;
		this.description = description;
		this.setStatus(status);
		this.categories = categories;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(final String title) {
		this.title = title;
	}

	public Set<String> getAuthors() {
		return this.authors;
	}

	public void setAuthors(final Set<String> authors) {
		this.authors = authors;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

	public BookStatus getStatus() {
		return this.status;
	}

	public void setStatus(final BookStatus status) {
		this.status = status;
	}

	public Set<BookCategory> getCategories() {
		return this.categories;
	}

	public void setCategories(final Set<BookCategory> categories) {
		this.categories = categories;
	}

}
