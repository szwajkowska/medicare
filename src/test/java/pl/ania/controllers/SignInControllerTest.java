package pl.ania.controllers;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

public class SignInControllerTest extends ControllerTest{

    @Test
    public void shouldCreateNewUser()throws Exception{

        mockMvc.perform(
                MockMvcRequestBuilders.post("/signIn")
                        .param("username", "user")
                        .param("password", "xxxx")
                        .param("confPassword", "xxxx")
                        .param("email", "xxx@xxx")
                        .with(SecurityMockMvcRequestPostProcessors.user("admin")))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection());

        Assertions.assertThat(userRepository.count()).isEqualTo(1);
        Assertions.assertThat(userRepository.findByUsername("user")).isPresent();
    }

}
