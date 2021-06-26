package book.book.repository;

import book.book.entity.Privilege;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrivilegeRepository extends JpaRepository<Privilege, Long> {

	Privilege findByName(String name);

}
