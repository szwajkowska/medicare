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
import pl.ania.security.User;
import pl.ania.security.UserRepository;

import java.security.Principal;
import java.util.Arrays;
import java.util.Date;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Application.class)
@SpringBootTest
@ActiveProfiles("test")
public class VisitsControllerTest {

    @Autowired
    private WebApplicationContext ctx;

    private MockMvc mockMvc;

    @Autowired
    private VisitRepository visitRepository;

    @Autowired
    private SpecializationRepository specializationRepository;

    @Autowired
    private DoctorRepository doctorRepository;

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
        userRepository.deleteAll(); //dlaczego taka kolejność?
        specializationRepository.deleteAll();
        doctorRepository.deleteAll();
        visitRepository.deleteAll();

    }

    @Test
    public void shouldFindVisitsByDoctorId() throws Exception{
        Specialization specialization = new Specialization("1", "chirurgia");
        specializationRepository.save(specialization);
        Doctor doctor = new Doctor("Jan", "Kowalski", "10", Arrays.asList(specialization));
        doctorRepository.save(doctor);
        User user = new User("1", "name", "xxx", "xxx@xxx");
        userRepository.save(user);
        Visit visit1 = new Visit("2", new Date(), doctor, user, specialization);
        Visit visit2 = new Visit("3", new Date(2012, 9, 13), doctor, null, specialization);
        visitRepository.save(visit1);
        visitRepository.save(visit2);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/visits?doctorId=10")
                        .with(SecurityMockMvcRequestPostProcessors.user("admin").roles("USER", "ADMIN")))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content()
                        .json("[{'id':'3','date':'3912-10-12T22:00:00.000+0000'}]"));
    }

    @Test
    public void shouldSetAppointment()throws Exception{
        Specialization specialization = new Specialization("1", "chirurgia");
        specializationRepository.save(specialization);
        Doctor doctor = new Doctor("Jan", "Kowalski", "10", Arrays.asList(specialization));
        doctorRepository.save(doctor);
        User user = new User("1", "name", "xxx", "xxx@xxx");
        userRepository.save(user);
        Visit visit = new Visit("1", new Date(), doctor, null, specialization);
        visitRepository.save(visit);
        Principal principal = () -> "name";

        mockMvc.perform(
                MockMvcRequestBuilders.post("/visits?visitId=1")
                        .principal(principal)
                        .with(SecurityMockMvcRequestPostProcessors.user("admin").roles("USER", "ADMIN")))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());

        Assertions.assertThat(visitRepository.findOne("1").getUser().getUsername()).isEqualTo(user.getUsername());
    }
}
