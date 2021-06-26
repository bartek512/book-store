package book.book;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Note, that since repositories and entities are in the same packages as the main application, there is no need of using {@link EnableJpaRepositories} nor
 * {@link EntityScan}
 */
@SpringBootApplication
public class BookStoreApplication extends SpringBootServletInitializer {

	public static void main(final String[] args) {
		SpringApplication.run(BookStoreApplication.class, args);
	}

}
