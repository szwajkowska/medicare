package pl.ania.controllers;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import pl.ania.domain.Specialization;

public class AdminSpecializationControllerTest extends ControllerTest {

    @Test
    public void shouldShowAdminSpecializationPage() throws Exception{
        mockMvc.perform(
                MockMvcRequestBuilders.get("/admin/specialization")
                        .with(SecurityMockMvcRequestPostProcessors.user("admin")))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void shouldAddSpecialization() throws Exception{

        mockMvc.perform(
                MockMvcRequestBuilders.post("/admin/specialization")
                        .param("specializationName", "kardiologia")
                        .with(SecurityMockMvcRequestPostProcessors.user("admin")))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());

        Assertions.assertThat(specializationRepository.count() == 1);
        Assertions.assertThat(specializationRepository.findAll().get(0).getSpecializationName().equals("kardiologia"));
    }

    @Test
    public void shouldDeleteDoctor() throws Exception{
        Specialization specialization = new Specialization("2", "chirurgia");
        specializationRepository.save(specialization);

        mockMvc.perform(
                MockMvcRequestBuilders.delete("/admin/specialization/2")
                        .with(SecurityMockMvcRequestPostProcessors.user("admin")))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());

        Assertions.assertThat(specializationRepository.count() == 0);
    }
}
