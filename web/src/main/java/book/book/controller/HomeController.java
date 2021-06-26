package book.book.controller;

import book.book.constants.ViewNames;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

	private static final String INFO = "info";

	private static final String INFO_TEXT = "Here You shall display whatever You want!";

	static final String MESSAGE = "message";

	static final String WELCOME = "This is a welcome page";

	@GetMapping(value = "/")
	public String welcome(final Model model) {
		model.addAttribute(MESSAGE, WELCOME);
		model.addAttribute(INFO, INFO_TEXT);
		return ViewNames.WELCOME_PAGE;
	}

}
