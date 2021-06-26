package book.book.rest.common;

import book.book.exception.BusinessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;


@ControllerAdvice(basePackages = "com.capgemini.jstk.book.rest.controller")
public class GlobalControllerAdvice {

    private static final Logger LOG = LoggerFactory.getLogger(GlobalControllerAdvice.class);

    @ResponseBody
    @ExceptionHandler(BusinessException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Error businessExceptionHandler(final Exception ex) {
        LOG.error("Error occurred", ex);
        return new Error(ex.getMessage());
    }

}
