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
import pl.ania.domain.Specialization;
import pl.ania.domain.SpecializationRepository;
import pl.ania.domain.doctors.Doctor;
import pl.ania.domain.doctors.DoctorRepository;

import java.util.Arrays;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Application.class)
@SpringBootTest
@ActiveProfiles("test")
public class AdminDoctorControllerTest {

    @Autowired
    private WebApplicationContext ctx;

    private MockMvc mockMvc;

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private SpecializationRepository specializationRepository;

    @Before
    public void setUp(){
        this.mockMvc = MockMvcBuilders
                .webAppContextSetup(ctx)
                .build();
    }

    @After
    public void clear(){
        doctorRepository.deleteAll();
        specializationRepository.deleteAll();
    }

    @Test
    public void shouldShowAdminDoctorPage() throws Exception{
        mockMvc.perform(
                MockMvcRequestBuilders.get("/admin/doctor")
                        .with(SecurityMockMvcRequestPostProcessors.user("admin")))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void shouldAddDoctor() throws Exception{
        Specialization specialization = new Specialization("1", "stomatologia");
        specializationRepository.save(specialization);

        mockMvc.perform(
                MockMvcRequestBuilders.post("/admin/doctor")
                        .param("specializationId", "1")
                        .param("firstName", "Jan")
                        .param("lastName", "Kowalski")
                        .with(SecurityMockMvcRequestPostProcessors.user("admin")))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());

        Assertions.assertThat(doctorRepository.count() == 1);
        Assertions.assertThat(specializationRepository.findOne("1").getDoctors().get(0).getFirstName()).isEqualTo("Jan");
    }

    @Test
    public void shouldDeleteDoctor() throws Exception{
        Specialization specialization = new Specialization("2", "chirurgia");
        specializationRepository.save(specialization);
        Doctor doctor = new Doctor("Jan", "Kowalski", "2", Arrays.asList(specialization));
        doctorRepository.save(doctor);

        mockMvc.perform(
                MockMvcRequestBuilders.delete("/admin/doctor/2")
                        .with(SecurityMockMvcRequestPostProcessors.user("admin")))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());

        Assertions.assertThat(doctorRepository.count() == 0);
        Assertions.assertThat(specializationRepository.count() == 1);
    }
}
