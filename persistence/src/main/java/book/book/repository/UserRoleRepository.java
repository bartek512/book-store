package book.book.repository;

import book.book.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRoleRepository extends JpaRepository<UserRole, Long> {

	UserRole findByName(String name);

}
