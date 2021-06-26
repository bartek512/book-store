package book.book.repository;

import book.book.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

	default Optional<User> findUserByName(final String name) {
		return this.findAll()
				.stream()
				.filter(u -> name.equalsIgnoreCase(u.getUserName()))
				.findFirst();
	}

}
