package pl.ania.controllers;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes = Application.class)
@SpringBootTest
@AutoConfigureMockMvc
public class AdminControllerTest {

//    @Autowired
//    private WebApplicationContext ctx;

    @Autowired
    private MockMvc mockMvc;

//    @Before
//    public void setUp(){
//        this.mockMvc = MockMvcBuilders
//                .webAppContextSetup(ctx)
////                .apply(springSecurity())
//                .build();
//    }

    @Test
    public void shouldShowAdminPage() throws Exception{
        mockMvc.perform(
                MockMvcRequestBuilders.get("/admin"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
