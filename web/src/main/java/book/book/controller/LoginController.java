package book.book.controller;


import book.book.constants.ViewNames;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller

public class LoginController {

    private final Logger log = LoggerFactory.getLogger(LoginController.class);

    /**
     * Redirect to login page
     */
    @GetMapping(value = "/login")
    public String prepareLoginPage() {
        log.info("redirecting to login page ");
        return ViewNames.LOGIN_PAGE;
    }
}
