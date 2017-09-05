package pl.ania.controllers;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
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
public class DoctorsControllerTest {

    @Autowired
    private WebApplicationContext ctx;

    private MockMvc mockMvc;

    @Autowired
    private SpecializationRepository specializationRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @Before
    public void setUp(){
        this.mockMvc = MockMvcBuilders
                .webAppContextSetup(ctx)
                .build();
    }

    @After
    public void clear(){
        specializationRepository.deleteAll();
        doctorRepository.deleteAll();
    }

    @Test
    public void shouldReturnDoctorsBySpecializationId() throws Exception{
        Specialization specialization = new Specialization("1", "chirurgia");
        specializationRepository.save(specialization);
        Doctor doctor1 = new Doctor("Jan", "Kowalski", "2", Arrays.asList(specialization));
        Doctor doctor2 = new Doctor("Adam", "Nowak", "3", Arrays.asList(specialization));
        doctorRepository.save(doctor1);
        doctorRepository.save(doctor2);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/doctors?specializationId=1")
                        .accept(MediaType.APPLICATION_JSON)
                        .with(SecurityMockMvcRequestPostProcessors.user("admin")))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content()
                        .json("[{'id':'2','firstName':'Jan','lastName':'Kowalski'}," +
                                "{'id':'3','firstName':'Adam','lastName':'Nowak'}]"));

    }
}
