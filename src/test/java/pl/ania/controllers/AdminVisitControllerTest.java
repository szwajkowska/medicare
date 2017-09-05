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
import pl.ania.domain.visits.Visit;
import pl.ania.domain.visits.VisitRepository;

import java.util.Arrays;
import java.util.Date;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Application.class)
@SpringBootTest
@ActiveProfiles("test")
public class AdminVisitControllerTest {

    @Autowired
    private WebApplicationContext ctx;

    private MockMvc mockMvc;

    @Autowired
    SpecializationRepository specializationRepository;

    @Autowired
    DoctorRepository doctorRepository;

    @Autowired
    VisitRepository visitRepository;

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
        visitRepository.deleteAll();
    }

    @Test
    public void shouldAddVisit() throws Exception{
        Specialization specialization = new Specialization("2", "kardiologia");
        specializationRepository.save(specialization);
        Doctor doctor = new Doctor("Jan", "Kowalski", "1", Arrays.asList(specialization));
        doctorRepository.save(doctor);


        mockMvc.perform(
                MockMvcRequestBuilders.post("/admin/visit")
                        .param("date", "2012-11-11T12:12")
                        .param("doctorId", "1")
                        .param("specializationId", "2")
                        .with(SecurityMockMvcRequestPostProcessors.user("admin")))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());

        Assertions.assertThat(visitRepository.count() == 1);
        Assertions.assertThat(specializationRepository.findOne("2").getSpecializationName()).isEqualTo("kardiologia");
        Assertions.assertThat(doctorRepository.findOne("1").getFirstName()).isEqualTo("Jan");
    }

    @Test
    public void shouldDeleteVisit()throws Exception{
        Specialization specialization = new Specialization("1", "chirurgia");
        specializationRepository.save(specialization);
        Doctor doctor = new Doctor("Jan", "Kowalski", "2", Arrays.asList(specialization));
        doctorRepository.save(doctor);
        Visit visit = new Visit("3", new Date(), doctor, specialization);
        visitRepository.save(visit);

        mockMvc.perform(
                MockMvcRequestBuilders.delete("/admin/visit/3")
                        .with(SecurityMockMvcRequestPostProcessors.user("admin")))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());

        Assertions.assertThat(visitRepository.count() == 0);
        Assertions.assertThat(specializationRepository.count() == 1);
        Assertions.assertThat(doctorRepository.count() == 1);
        Assertions.assertThat(doctorRepository.findOne("2").getSpecializations().get(0)
                .getSpecializationName()).isEqualTo("chirurgia");
    }
}
