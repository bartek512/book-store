package book.book;

import book.book.controller.HomeControllerTest;
import book.book.rest.UserRestControllerTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({HomeControllerTest.class, UserRestControllerTest.class})
public class AllTestsSuite {}
