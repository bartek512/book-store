package book.book.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import static org.hamcrest.core.StringContains.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class HomeControllerTest {


    private MockMvc mockMvc;

    @Before
    public void setup() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/src/main/resources/templates");
        viewResolver.setSuffix(".html");
        this.mockMvc = MockMvcBuilders.standaloneSetup(new HomeController()).setViewResolvers(viewResolver)
                .build();
    }

    @Test
    public void testHomePage() throws Exception {
        // given when
        final ResultActions resultActions = this.mockMvc.perform(get("/"));
        // then
        resultActions.andExpect(status().isOk())//
                .andExpect(view().name("books"))//
                .andDo(print())//
                .andExpect(model().attribute(HomeController.MESSAGE, HomeController.WELCOME))//
                .andExpect(content().string(containsString("")));
    }

}
