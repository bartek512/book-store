package book.book.controller;

import book.book.constants.ViewNames;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

@Controller
public class ApplicationErrorController implements ErrorController {

	private static final String ERROR = "error";

	@RequestMapping("/error")
	public ModelAndView handleError(final HttpServletRequest request) {
		final ModelAndView modelAndView = new ModelAndView();
		final Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
		String reason = "undefined error";
		if (status != null) {
			final int statusCode = Integer.parseInt(status.toString());
			if (statusCode == HttpStatus.NOT_FOUND.value()) {
				reason = "404 - NOT FOUND";
			} else if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
				reason = "500 - INTERNAL SERVER ERROR";
			}
		}
		modelAndView.addObject(ERROR, reason);
		modelAndView.setViewName(ViewNames.ERROR_PAGE);
		return modelAndView;
	}

	@Override
	public String getErrorPath() {
		return null; // not used anymore
	}

}
