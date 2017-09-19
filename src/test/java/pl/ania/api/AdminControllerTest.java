package pl.ania.api;

import org.junit.Test;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

public class AdminControllerTest extends ControllerTest{

    @Test
    public void shouldShowAdminPage() throws Exception{
        mockMvc.perform(
                MockMvcRequestBuilders.get("/admin").with(SecurityMockMvcRequestPostProcessors.user("admin")))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
