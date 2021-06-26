package book.book.repository;

import book.book.config.PersistenceTestConfig;
import book.book.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = PersistenceTestConfig.class)
public class UserRepositoryTest {

	@Autowired
	private UserRepository userRepository;

	@Test
	public void testShouldFindUsersByName() {
		// given
		final String userName = "admin";
		// when
		final Optional<User> userEntity = this.userRepository.findUserByName(userName);
		// then
		assertTrue(userEntity.isPresent());
		assertNotNull(userEntity.get());
		assertEquals(userName, userEntity.get()
				.getUserName());
	}

}
