package pl.ania.controllers;

import org.assertj.core.api.Assertions;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import pl.ania.Application;
import pl.ania.security.UserRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Application.class)
@SpringBootTest
@ActiveProfiles("test")
public class SignInControllerTest {

    @Autowired
    private WebApplicationContext ctx;

    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Before
    public void setUp(){
        this.mockMvc = MockMvcBuilders
                .webAppContextSetup(ctx)
                .build();
    }

    @After
    public void clear(){
        userRepository.deleteAll();
    }

    @Test
    public void shouldCreateNewUser()throws Exception{

        mockMvc.perform(
                MockMvcRequestBuilders.post("/signIn")
                        .param("username", "user")
                        .param("password", "xxxx")
                        .param("confPassword", "xxxx")
                        .param("email", "xxx@xxx")
                        .with(SecurityMockMvcRequestPostProcessors.user("admin").roles("USER", "ADMIN")))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection());

        Assertions.assertThat(userRepository.findAll()).hasSize(1);
        Assertions.assertThat(userRepository.findByUsername("user")).isPresent();
    }

}
