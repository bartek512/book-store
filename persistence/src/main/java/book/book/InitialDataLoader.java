package book.book;

import book.book.entity.Book;
import book.book.entity.Privilege;
import book.book.entity.User;
import book.book.entity.UserRole;
import book.book.enumerations.BookCategory;
import book.book.enumerations.BookStatus;
import book.book.repository.BookRepository;
import book.book.repository.PrivilegeRepository;
import book.book.repository.UserRepository;
import book.book.repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Loads data with pre-defined data after context is being loaded (on {@link ContextRefreshedEvent})
 */
@Component
public class InitialDataLoader implements ApplicationListener<ContextRefreshedEvent> {

	private final BookRepository bookRepository;

	private final UserRepository userRepository;

	private final UserRoleRepository userRoleRepository;

	private final PrivilegeRepository privilegeRepository;

	private final PasswordEncoder passwordEncoder;

	@Autowired
	public InitialDataLoader(final BookRepository bookRepository, final UserRepository userRepository, final UserRoleRepository userRoleRepository,
			final PrivilegeRepository privilegeRepository) {
		this.bookRepository = bookRepository;
		this.userRepository = userRepository;
		this.userRoleRepository = userRoleRepository;
		this.privilegeRepository = privilegeRepository;
		this.passwordEncoder = new BCryptPasswordEncoder(11);
	}

	@Override
	@Transactional
	public void onApplicationEvent(final ContextRefreshedEvent event) {
		// Books
		createBookIfNotFound("Java dla początkujących", new HashSet<>(Arrays.asList("Kacper Koło", "Andżelika Wielka")),
				"Najlepsza książka dla wszystkich zainteresowanych Javą, ale bojących się zapytać", BookStatus.FREE,
				EnumSet.of(BookCategory.MYSTERY, BookCategory.SELF_HELP));
		createBookIfNotFound("Kamień i szkło", new HashSet<>(Arrays.asList("Kacper Koło", "Andżelika Wielka")),
				"Kamień i szkło to romans, jakiego jeszcze nie czytałeś. Miłość w hucie z wiosną w tle", BookStatus.FREE,
				EnumSet.of(BookCategory.MYSTERY, BookCategory.SELF_HELP));
		createBookIfNotFound("Ból i Cierpienie", new HashSet<>(Arrays.asList("Robert R.R. Raspberry", "Maya B.I. Ernat")),
				"Ich szczęśliwe życie dobiega do końca, gdy samolot, którym podróżowali na wakacje, rozbija się na bezludnej wyspie. "
						+ "Muszą stanąć do walki z zadaniami, które rzuca przed nimi los. Przezwyciężyć swoje lęki i poskromić swoje obawy. "
						+ "Czy uda im się dotrwać do końca? Czy otrzymają ratunek? Wydana w lutym 2021 książka trzyma w napięciu do ostatniej litery",
				BookStatus.FREE, EnumSet.of(BookCategory.MYSTERY, BookCategory.ACTION_AND_ADVENTURE, BookCategory.HORROR));
		// Privileges
		final Privilege readPrivilege = createPrivilegeIfNotFound("READ_PRIVILEGE");
		final Privilege writePrivilege = createPrivilegeIfNotFound("WRITE_PRIVILEGE");
		// Roles
		final List<Privilege> adminPrivileges = Arrays.asList(readPrivilege, writePrivilege);
		createRoleIfNotFound("ROLE_ADMIN", adminPrivileges);
		createRoleIfNotFound("ROLE_USER", Collections.singletonList(readPrivilege));
		// User roles
		final UserRole adminRole = this.userRoleRepository.findByName("ROLE_ADMIN");
		final UserRole userRole = this.userRoleRepository.findByName("ROLE_USER");
		// Users
		final User admin = new User();
		admin.setUserName("admin");
		admin.setPassword(this.passwordEncoder.encode("test"));
		admin.setRoles(Collections.singletonList(adminRole));
		this.userRepository.save(admin);
		final User user = new User();
		user.setUserName("user");
		user.setPassword(this.passwordEncoder.encode("test"));
		user.setRoles(Collections.singletonList(userRole));
		this.userRepository.save(user);
	}

	private void createBookIfNotFound(final String title, final Set<String> authors, final String description, final BookStatus status,
			final Set<BookCategory> categories) {
		final List<Book> books = this.bookRepository.findBookByTitle(title);
		if (books.isEmpty()) {
			final Book book = new Book();
			book.setTitle(title);
			book.setAuthors(authors);
			book.setCategories(categories);
			book.setDescription(description);
			book.setStatus(status);
			this.bookRepository.save(book);
		}
	}

	private Privilege createPrivilegeIfNotFound(final String name) {
		Privilege privilege = this.privilegeRepository.findByName(name);
		if (privilege == null) {
			privilege = new Privilege();
			privilege.setName(name);
			privilege = this.privilegeRepository.save(privilege);
		}
		return privilege;
	}

	private void createRoleIfNotFound(final String name, final Collection<Privilege> privileges) {
		UserRole role = this.userRoleRepository.findByName(name);
		if (role == null) {
			role = new UserRole();
			role.setName(name);
			role.setPrivileges(privileges);
			this.userRoleRepository.save(role);
		}
	}

}
