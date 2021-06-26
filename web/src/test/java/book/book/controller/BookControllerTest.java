package book.book.controller;


import book.book.service.impl.BookServiceImpl;
import book.book.to.BookTo;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class BookControllerTest {


    private MockMvc mockMvc;

    @Mock
    private BookServiceImpl bookService;


    @Before
    public void setup() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/src/main/resources/templates");
        viewResolver.setSuffix(".html");
        this.mockMvc = MockMvcBuilders.standaloneSetup(new BookController(this.bookService)).setViewResolvers(viewResolver)
                .build();
    }

    @Test
    public void testBooksPage() throws Exception {
        //given
        final ResultActions resultActions = this.mockMvc.perform(get("/books"));
        List<BookTo> books = new ArrayList<>();

        // then
        resultActions.andExpect(status().isOk())//
                .andExpect(view().name("books"))//
                .andDo(print())
                .andExpect(model().attribute("bookList", books));
    }

    @Test
    public void testAddBookPage() throws Exception {
        //given
        final ResultActions resultActions = this.mockMvc.perform(get("/books/add"));


        // then
        resultActions.andExpect(status().isOk())//
                .andExpect(view().name("addBook"))//
                .andDo(print())
                .andExpect(model().attributeExists("newBook"));
    }

    @Test
    public void testAddBookPageAfterAddingBook() throws Exception {
        //given
        final ResultActions resultActions = this.mockMvc.perform(post("/add"));
        BookTo book = new BookTo();
        // then
        resultActions.andExpect(status().isOk())//
                .andExpect(view().name("books"))//
                .andDo(print())
                .andExpect(model().attributeExists("newBook"));
    }

    @Test
    public void testRemoveBookPage() throws Exception {
        //given
        final ResultActions resultActions = this.mockMvc.perform(get("/removeBook"));

        // then
        resultActions.andExpect(status().isOk())//
                .andExpect(view().name("removeBook"))//
                .andDo(print());
    }

    @Test
    public void testLoginPage() throws Exception {
        //given
        final ResultActions resultActions = this.mockMvc.perform(get("/login"));

        // then
        resultActions.andExpect(status().isOk())//
                .andExpect(view().name("login"))//
                .andDo(print());
    }

    @Test
    public void testErrorPage() throws Exception {
        //given
        final ResultActions resultActions = this.mockMvc.perform(get("/error"));

        // then
        resultActions.andExpect(status().isOk())//
                .andExpect(view().name("error"))//
                .andDo(print());
    }


}
