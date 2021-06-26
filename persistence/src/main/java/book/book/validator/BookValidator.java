package book.book.validator;

import book.book.to.BookTo;
import book.book.exception.BusinessException;
import org.springframework.stereotype.Component;

@Component
public class BookValidator {
    public boolean validate(BookTo bookTo) {
        if (bookTo.getTitle().equals("")) {
            throw new BusinessException("Book must have a title");
        }
        return true;
    }
}
