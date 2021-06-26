package book.book.entity;

import book.book.enumerations.BookCategory;
import book.book.enumerations.BookStatus;
import com.fasterxml.jackson.annotation.JsonCreator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "BOOK")
public class Book implements Serializable {

    private static final long serialVersionUID = 4062780743333210058L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, length = 50)
    private String title;

    @ElementCollection
    @CollectionTable(name = "book_author", joinColumns = {@JoinColumn(name = "book_id")})
    @Column(name = "author", length = 40, nullable = false)
    private Set<String> authors;

    @Enumerated(EnumType.STRING)
    private BookStatus status;

    @Column(length = 600)
    private String description;

    @ElementCollection
    @JoinTable(name = "book_category", joinColumns = @JoinColumn(name = "book_id"))
    @Column(name = "interest", nullable = false)
    @Enumerated(EnumType.STRING)
    private Set<BookCategory> categories;

    public Book() {
        // for hibernate only
    }

    @JsonCreator
    public Book(final Long id, final String title, final Set<String> authors, final String description, final BookStatus status,
                final Set<BookCategory> categories) {
        this.id = id;
        this.title = title;
        this.authors = authors;
        this.description = description;
        this.categories = categories;
        this.setStatus(status);
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

    public BookStatus getStatus() {
        return this.status;
    }

    public void setStatus(final BookStatus status) {
        this.status = status;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public Set<BookCategory> getCategories() {
        return this.categories;
    }

    public void setCategories(final Set<BookCategory> categories) {
        this.categories = categories;
    }

}
