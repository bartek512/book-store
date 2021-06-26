package book.book.rest;

import book.book.rest.controller.UserRestController;
import book.book.service.impl.UserServiceImpl;
import book.book.to.UserTo;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.emptyString;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class UserRestControllerTest {

	private MockMvc mockMvc;

	@Mock
	private UserServiceImpl userService;

	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.standaloneSetup(new UserRestController(this.userService))
				.build();
	}

	@Test
	public void shouldFindAll() throws Exception {
		// given
		final List<UserTo> resultList = new ArrayList<>();
		resultList.add(new UserTo(Long.valueOf(1), "user", "user"));
		when(this.userService.findAllUsers()).thenReturn(resultList);
		// when
		this.mockMvc.perform(get("/rest/users"))//
				.andExpect(status().isOk())//
				.andDo(print())//
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))//
				.andExpect(jsonPath("$[0].userName", is("user")));
		// and others check for right json
	}

	@Test
	public void shouldNotFindByName() throws Exception {
		// given
		when(this.userService.findUserByName("dummy")).thenReturn(null);
		// when
		this.mockMvc.perform(get("/rest/users/{name}", "dummy"))//
				.andExpect(status().isOk())//
				.andDo(print())//
				.andExpect(content().string(is(emptyString())));
	}

	@Test
	public void shouldFindByName() throws Exception {
		// given
		final UserTo userToReturn = new UserTo(Long.valueOf(1), "user", "user");
		when(this.userService.findUserByName("user")).thenReturn(userToReturn);
		// when
		this.mockMvc.perform(get("/rest/users/{name}", "user"))//
				.andExpect(status().isOk())//
				.andDo(print())//
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))//
				.andExpect(jsonPath("userName", is("user")));
		// and others check for right json
	}

}
