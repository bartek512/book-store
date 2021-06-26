package book.book.to;

import book.book.enumerations.BookCategory;
import book.book.enumerations.BookStatus;

import java.util.Set;

public class SearchBookTo {

    private String title;

    private Set<BookCategory> categories;

    private BookStatus status;

    public SearchBookTo(String title, Set<BookCategory> categories, BookStatus status) {
        this.title = title;
        this.categories = categories;
        this.status = status;
    }

    public SearchBookTo(String title, Set<BookCategory> categories) {
        this.title = title;
        this.categories = categories;
    }

    public SearchBookTo(String title, BookStatus status) {
        this.title = title;
        this.status = status;
    }

    public SearchBookTo(Set<BookCategory> categories, BookStatus status) {
        this.categories = categories;
        this.status = status;
    }

    public SearchBookTo(String title) {
        this.title = title;
    }

    public SearchBookTo(Set<BookCategory> categories) {
        this.categories = categories;
    }

    public SearchBookTo(BookStatus status) {
        this.status = status;
    }

    public SearchBookTo() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Set<BookCategory> getCategories() {
        return categories;
    }

    public void setCategories(Set<BookCategory> categories) {
        this.categories = categories;
    }

    public BookStatus getStatus() {
        return status;
    }

    public void setStatus(BookStatus status) {
        this.status = status;
    }
}
