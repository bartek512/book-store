package book.book.rest;


import book.book.enumerations.BookCategory;
import book.book.enumerations.BookStatus;
import book.book.rest.common.GlobalControllerAdvice;
import book.book.rest.controller.BookRestController;
import book.book.service.impl.BookServiceImpl;
import book.book.to.BookTo;
import book.book.to.SearchBookTo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(MockitoJUnitRunner.class)
@Import(GlobalControllerAdvice.class)
@SpringBootTest
public class BookRestControllerTest {


    private MockMvc mockMvc;

    @Mock
    private BookServiceImpl bookService;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(new BookRestController(bookService)).setControllerAdvice(new GlobalControllerAdvice())
                .build();
    }


    @Test
    public void shouldFindAll() throws Exception {
        // given
        final List<BookTo> resultList = new ArrayList<>();
        Set<String> authors = new HashSet<>();
        authors.add("Kacper Koło");
        authors.add("Andżelika Wielka");
        Set<BookCategory> category = new HashSet<>();
        category.add(BookCategory.MYSTERY);
        category.add(BookCategory.SELF_HELP);
        resultList.add(new BookTo(1L, "Java dla początkujących", authors, "Najlepsza książka dla wszystkich zainteresowanych Javą, ale bojących się zapytać",
                BookStatus.FREE, category));
        when(this.bookService.findAllBooks()).thenReturn(resultList);
        // when
        this.mockMvc.perform(get("/rest/books"))//
                .andExpect(status().isOk())//
                .andDo(print())//
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))//
                .andExpect(jsonPath("$[0].title", is("Java dla początkujących")))
                .andExpect(jsonPath("$[0].authors", containsInAnyOrder("Kacper Koło", "Andżelika Wielka")))
                .andExpect(jsonPath("$[0].description", is("Najlepsza książka dla wszystkich zainteresowanych Javą, ale bojących się zapytać")))
                .andExpect(jsonPath("$[0].status", is(BookStatus.FREE.name())))
                .andExpect(jsonPath("$[0].categories", containsInAnyOrder(BookCategory.MYSTERY.name(), BookCategory.SELF_HELP.name())));
    }

    @Test
    public void shouldReturnEmptyList() throws Exception {
        // given
        final List<BookTo> resultList = new ArrayList<>();

        when(this.bookService.findAllBooks()).thenReturn(resultList);
        // when
        this.mockMvc.perform(get("/rest/books"))//
                .andExpect(status().isOk())//
                .andDo(print())//
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))//
                .andExpect(jsonPath("$", empty()));
    }

    @Test
    public void shouldNotFindByTitle() throws Exception {

        // given
        when(this.bookService.findBooksByTitle("dummy")).thenReturn(null);

        // when
        this.mockMvc.perform(get("/rest//booksByTitle/{title}", "dummy"))//
                .andExpect(status().isOk())//
                .andDo(print())//
                .andExpect(content().string(is(emptyString())));
    }

    @Test
    public void shouldFindByTitle() throws Exception {
        // given
        final List<BookTo> resultList = new ArrayList<>();
        Set<String> authors = new HashSet<>();
        authors.add("Kacper Koło");
        authors.add("Andżelika Wielka");
        Set<BookCategory> category = new HashSet<>();
        category.add(BookCategory.MYSTERY);
        category.add(BookCategory.SELF_HELP);
        resultList.add(new BookTo(1L, "Java dla poczatkujacych", authors, "Najlepsza książka dla wszystkich zainteresowanych Javą, ale bojących się zapytać",
                BookStatus.FREE, category));
        when(this.bookService.findBooksByTitle("Java dla poczatkujacych")).thenReturn((resultList));
        // when
        this.mockMvc.perform(get("/rest/booksByTitle/{title}", "Java dla poczatkujacych"))//
                .andExpect(status().isOk())//
                .andDo(print())//
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))//
                .andExpect(jsonPath("$[0].title", is("Java dla poczatkujacych")))
                .andExpect(jsonPath("$[0].authors", containsInAnyOrder("Kacper Koło", "Andżelika Wielka")))
                .andExpect(jsonPath("$[0].description", is("Najlepsza książka dla wszystkich zainteresowanych Javą, ale bojących się zapytać")))
                .andExpect(jsonPath("$[0].status", is(BookStatus.FREE.name())))
                .andExpect(jsonPath("$[0].categories", containsInAnyOrder(BookCategory.MYSTERY.name(), BookCategory.SELF_HELP.name())));
    }

    @Test
    public void shouldFindById() throws Exception {
        // given
        final List<BookTo> resultList = new ArrayList<>();
        Set<String> authors = new HashSet<>();
        authors.add("Kacper Koło");
        authors.add("Andżelika Wielka");
        Set<BookCategory> category = new HashSet<>();
        category.add(BookCategory.MYSTERY);
        category.add(BookCategory.SELF_HELP);
        resultList.add(new BookTo(1L, "Java dla poczatkujacych", authors, "Najlepsza książka dla wszystkich zainteresowanych Javą, ale bojących się zapytać",
                BookStatus.FREE, category));
        when(this.bookService.findById(1L)).thenReturn(resultList.get(0));

        // when
        this.mockMvc.perform(get("/rest/books/1"))//
                .andExpect(status().isOk())//
                .andDo(print())//
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))//
                .andExpect(jsonPath("title", is("Java dla poczatkujacych")))
                .andExpect(jsonPath("authors", containsInAnyOrder("Kacper Koło", "Andżelika Wielka")))
                .andExpect(jsonPath("description", is("Najlepsza książka dla wszystkich zainteresowanych Javą, ale bojących się zapytać")))
                .andExpect(jsonPath("status", is(BookStatus.FREE.name())))
                .andExpect(jsonPath("categories", containsInAnyOrder(BookCategory.MYSTERY.name(), BookCategory.SELF_HELP.name())));
    }

    @Test
    public void shouldNotFindById() throws Exception {
        // given

        when(this.bookService.findById(1L)).thenReturn(null);

        // when
        this.mockMvc.perform(get("/rest/books/1"))//
                .andExpect(status().isOk())//
                .andDo(print())//
                .andExpect(content().string(is(emptyString())));
    }

    @Test
    public void shouldFilterByTitleCategoryAndStatusAndShouldReturnEmptyFile() throws Exception {

        // given
        when(this.bookService.filterBooks(Mockito.any(SearchBookTo.class))).thenReturn((new ArrayList<>()));

        Set<BookCategory> category = new HashSet<>();
        category.add(BookCategory.SATIRE);
        SearchBookTo searchBookTo = new SearchBookTo("wiedzmin", category, BookStatus.FREE);

        //when
        this.mockMvc.perform(post("/rest/filter").content(asJsonString(searchBookTo)).contentType(MediaType.APPLICATION_JSON))//
                .andExpect(status().isOk())//
                .andDo(print())//
                .andExpect(jsonPath("$", empty()));
    }

    @Test
    public void shouldFilterByTitleCategoryAndStatusAndShouldReturnRightBook() throws Exception {

        // given
        List<BookTo> resultList = new ArrayList<>();
        Set<String> authors = new HashSet<>();
        authors.add("Kacper Koło");
        authors.add("Andżelika Wielka");
        Set<BookCategory> category = new HashSet<>();
        category.add(BookCategory.MYSTERY);
        category.add(BookCategory.SELF_HELP);

        Set<BookCategory> categoryToFilter = new HashSet<>();
        categoryToFilter.add(BookCategory.MYSTERY);
        SearchBookTo searchBookTo = new SearchBookTo("Java dla poczatkujacych", categoryToFilter, BookStatus.FREE);
        resultList.add(new BookTo(2L, "Java dla poczatkujacych", authors, "Najlepsza książka dla wszystkich zainteresowanych Javą, ale bojących się zapytać",
                BookStatus.FREE, category));

        when(this.bookService.filterBooks(Mockito.any(SearchBookTo.class))).thenReturn((resultList));

        //when
        this.mockMvc.perform(post("/rest/filter").content(asJsonString(searchBookTo)).contentType(MediaType.APPLICATION_JSON))//
                .andExpect(status().isOk())//
                .andDo(print())//
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].title", is("Java dla poczatkujacych")))
                .andExpect(jsonPath("$[0].authors", containsInAnyOrder("Kacper Koło", "Andżelika Wielka")))
                .andExpect(jsonPath("$[0].description", is("Najlepsza książka dla wszystkich zainteresowanych Javą, ale bojących się zapytać")))
                .andExpect(jsonPath("$[0].status", is(BookStatus.FREE.name())))
                .andExpect(jsonPath("$[0].categories", containsInAnyOrder(BookCategory.MYSTERY.name(), BookCategory.SELF_HELP.name())));
    }

    @Test
    public void shouldFilterByTitleAndShouldReturnRightBook() throws Exception {

        // given
        List<BookTo> resultList = new ArrayList<>();
        Set<String> authors = new HashSet<>();
        authors.add("Kacper Koło");
        authors.add("Andżelika Wielka");
        Set<BookCategory> category = new HashSet<>();
        category.add(BookCategory.MYSTERY);
        category.add(BookCategory.SELF_HELP);

        SearchBookTo searchBookTo = new SearchBookTo("Java dla poczatkujacych");
        resultList.add(new BookTo(2L, "Java dla poczatkujacych", authors, "Najlepsza książka dla wszystkich zainteresowanych Javą, ale bojących się zapytać",
                BookStatus.FREE, category));

        when(this.bookService.filterBooks(Mockito.any(SearchBookTo.class))).thenReturn((resultList));

        //when
        this.mockMvc.perform(post("/rest/filter").content(asJsonString(searchBookTo)).contentType(MediaType.APPLICATION_JSON))//
                .andExpect(status().isOk())//
                .andDo(print())//
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].title", is("Java dla poczatkujacych")))
                .andExpect(jsonPath("$[0].authors", containsInAnyOrder("Kacper Koło", "Andżelika Wielka")))
                .andExpect(jsonPath("$[0].description", is("Najlepsza książka dla wszystkich zainteresowanych Javą, ale bojących się zapytać")))
                .andExpect(jsonPath("$[0].status", is(BookStatus.FREE.name())))
                .andExpect(jsonPath("$[0].categories", containsInAnyOrder(BookCategory.MYSTERY.name(), BookCategory.SELF_HELP.name())));
    }

    @Test
    public void shouldFilterByStatusAndShouldReturnRightBook() throws Exception {

        // given
        List<BookTo> resultList = new ArrayList<>();
        Set<String> authors = new HashSet<>();
        authors.add("Kacper Koło");
        authors.add("Andżelika Wielka");
        Set<BookCategory> category = new HashSet<>();
        category.add(BookCategory.MYSTERY);
        category.add(BookCategory.SELF_HELP);

        SearchBookTo searchBookTo = new SearchBookTo(BookStatus.MISSING);
        resultList.add(new BookTo(2L, "Java dla poczatkujacych", authors, "Najlepsza książka dla wszystkich zainteresowanych Javą, ale bojących się zapytać",
                BookStatus.MISSING, category));

        when(this.bookService.filterBooks(Mockito.any(SearchBookTo.class))).thenReturn((resultList));

        //when
        this.mockMvc.perform(post("/rest/filter").content(asJsonString(searchBookTo)).contentType(MediaType.APPLICATION_JSON))//
                .andExpect(status().isOk())//
                .andDo(print())//
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].title", is("Java dla poczatkujacych")))
                .andExpect(jsonPath("$[0].authors", containsInAnyOrder("Kacper Koło", "Andżelika Wielka")))
                .andExpect(jsonPath("$[0].description", is("Najlepsza książka dla wszystkich zainteresowanych Javą, ale bojących się zapytać")))
                .andExpect(jsonPath("$[0].status", is(BookStatus.MISSING.name())))
                .andExpect(jsonPath("$[0].categories", containsInAnyOrder(BookCategory.MYSTERY.name(), BookCategory.SELF_HELP.name())));
    }

    @Test
    public void shouldFilterByCategoryAndShouldReturnRightBook() throws Exception {

        // given
        List<BookTo> resultList = new ArrayList<>();

        Set<String> authors = new HashSet<>();
        authors.add("Kacper Koło");
        authors.add("Andżelika Wielka");
        Set<BookCategory> category = new HashSet<>();
        category.add(BookCategory.SATIRE);
        Set<BookCategory> categoryToFilter = new HashSet<>();
        categoryToFilter.add(BookCategory.SATIRE);
        SearchBookTo searchBookTo = new SearchBookTo(categoryToFilter);
        resultList.add(new BookTo(2L, "Java dla poczatkujacych", authors, "Najlepsza książka dla wszystkich zainteresowanych Javą, ale bojących się zapytać",
                BookStatus.FREE, category));

        when(this.bookService.filterBooks(Mockito.any(SearchBookTo.class))).thenReturn((resultList));

        //when
        this.mockMvc.perform(post("/rest/filter").content(asJsonString(searchBookTo)).contentType(MediaType.APPLICATION_JSON))//
                .andExpect(status().isOk())//
                .andDo(print())//
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].title", is("Java dla poczatkujacych")))
                .andExpect(jsonPath("$[0].authors", containsInAnyOrder("Kacper Koło", "Andżelika Wielka")))
                .andExpect(jsonPath("$[0].description", is("Najlepsza książka dla wszystkich zainteresowanych Javą, ale bojących się zapytać")))
                .andExpect(jsonPath("$[0].status", is(BookStatus.FREE.name())))
                .andExpect(jsonPath("$[0].categories", containsInAnyOrder(BookCategory.SATIRE.name())));
    }

    @Test
    public void shouldFilterByCategoryAndStatusAndShouldReturnRightBook() throws Exception {

        // given
        List<BookTo> resultList = new ArrayList<>();
        Set<String> authors = new HashSet<>();
        authors.add("Kacper Koło");
        authors.add("Andżelika Wielka");
        Set<BookCategory> category = new HashSet<>();
        category.add(BookCategory.SATIRE);
        Set<BookCategory> categoryToFilter = new HashSet<>();
        categoryToFilter.add(BookCategory.SATIRE);
        SearchBookTo searchBookTo = new SearchBookTo(categoryToFilter, BookStatus.FREE);
        resultList.add(new BookTo(2L, "Java dla poczatkujacych", authors, "Najlepsza książka dla wszystkich zainteresowanych Javą, ale bojących się zapytać",
                BookStatus.FREE, category));

        when(this.bookService.filterBooks(Mockito.any(SearchBookTo.class))).thenReturn((resultList));

        //when
        this.mockMvc.perform(post("/rest/filter").content(asJsonString(searchBookTo)).contentType(MediaType.APPLICATION_JSON))//
                .andExpect(status().isOk())//
                .andDo(print())//
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].title", is("Java dla poczatkujacych")))
                .andExpect(jsonPath("$[0].authors", containsInAnyOrder("Kacper Koło", "Andżelika Wielka")))
                .andExpect(jsonPath("$[0].description", is("Najlepsza książka dla wszystkich zainteresowanych Javą, ale bojących się zapytać")))
                .andExpect(jsonPath("$[0].status", is(BookStatus.FREE.name())))
                .andExpect(jsonPath("$[0].categories", containsInAnyOrder(BookCategory.SATIRE.name())));
    }

    @Test
    public void shouldPostNewBook() throws Exception {
        //given
        Set<BookCategory> category = new HashSet<>();
        Set<String> authors = new HashSet<>();
        category.add(BookCategory.SATIRE);
        authors.add("Sapkowski");
        BookTo bookTo = new BookTo(1L, "wiedzmin", authors, "Ksiazka o wiedzminie", BookStatus.FREE, category);

        when(this.bookService.saveBook(Mockito.any(BookTo.class))).thenReturn(bookTo);

        //when
        this.mockMvc.perform(post("/rest/add").content(asJsonString(bookTo)).contentType(MediaType.APPLICATION_JSON))//
                .andExpect(status().isOk())//
                .andDo(print())//
                .andExpect(jsonPath("title", is("wiedzmin")))
                .andExpect(jsonPath("authors", containsInAnyOrder("Sapkowski")))
                .andExpect(jsonPath("description", is("Ksiazka o wiedzminie")))
                .andExpect(jsonPath("status", is(BookStatus.FREE.name())))
                .andExpect(jsonPath("categories", containsInAnyOrder(BookCategory.SATIRE.name())));
    }

    @Test
    public void shouldNotPostNewBookAndThrowException() throws Exception {
        //given
        Set<BookCategory> category = new HashSet<>();
        Set<String> authors = new HashSet<>();
        category.add(BookCategory.SATIRE);
        authors.add("Sapkowski");
        BookTo bookTo = new BookTo(1L, null, authors, "Ksiazka o wiedzminie", BookStatus.FREE, category);

        //when
        this.mockMvc.perform(post("/rest/add").content(asJsonString(bookTo)).contentType(MediaType.APPLICATION_JSON))//
                .andExpect(status().isBadRequest())//
                .andDo(print())//
                .andExpect(mvcResult -> assertEquals("Book must have a title",
                        mvcResult.getResolvedException().getMessage()));
    }

    private String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

