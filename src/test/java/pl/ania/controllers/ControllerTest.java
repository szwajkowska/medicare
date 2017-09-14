package pl.ania.controllers;


import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import pl.ania.Application;
import pl.ania.domain.SpecializationRepository;
import pl.ania.domain.doctors.DoctorRepository;
import pl.ania.domain.visits.VisitRepository;
import pl.ania.security.UserRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Application.class)
@SpringBootTest
@ActiveProfiles("test")
public abstract class ControllerTest {

    @Autowired
    WebApplicationContext ctx;

    MockMvc mockMvc;

    @Autowired
    VisitRepository visitRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    SpecializationRepository specializationRepository;

    @Autowired
    DoctorRepository doctorRepository;

    @Before
    public void setUp() {
        this.mockMvc = MockMvcBuilders
                .webAppContextSetup(ctx)
                .build();
    }

    @After
    public void clear() {
        userRepository.deleteAll();
        visitRepository.deleteAll();
        specializationRepository.deleteAll();
        doctorRepository.deleteAll();
    }
}
