package book.book.rest.controller;

import book.book.exception.BusinessException;
import book.book.rest.common.GlobalControllerAdvice;
import book.book.service.UserService;
import book.book.to.UserTo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * This is only an example, how to write some REST endpoints
 */
@RestController
@RequestMapping("/rest")
public class UserRestController {

	private final UserService userService;

	@Autowired
	public UserRestController(final UserService userService) {
		this.userService = userService;
	}

	@GetMapping("/users")
	public ResponseEntity<List<UserTo>> findAllUsers() {
		final List<UserTo> allUsers = this.userService.findAllUsers();
		return ResponseEntity.ok()
				.body(allUsers);
	}

	@GetMapping("/users/{name}")
	public ResponseEntity<UserTo> findUser(@PathVariable("name") final String name) {
		final UserTo user = this.userService.findUserByName(name);
		return ResponseEntity.ok()
				.body(user);
	}

	/**
	 * @see GlobalControllerAdvice
	 */
	@GetMapping("/generate-exception")
	public ResponseEntity<List<UserTo>> generateException() {
		throw new BusinessException("This endpoint generates exception, which should be handled");
	}

}
