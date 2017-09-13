package pl.ania.controllers;


import com.fasterxml.jackson.databind.util.ISO8601DateFormat;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import pl.ania.domain.Specialization;
import pl.ania.domain.doctors.Doctor;
import pl.ania.domain.visits.Visit;
import pl.ania.security.User;

import java.security.Principal;
import java.util.Arrays;
import java.util.Date;

public class VisitsControllerTest extends ControllerTest{

    @Test
    public void shouldFindVisitsByDoctorId() throws Exception{
        Specialization specialization = new Specialization("1", "chirurgia");
        specializationRepository.save(specialization);
        Doctor doctor = new Doctor("Jan", "Kowalski", "10", Arrays.asList(specialization));
        doctorRepository.save(doctor);
        User user = new User("1", "name", "xxx", "xxx@xxx");
        userRepository.save(user);
        Visit visit1 = new Visit("2", new Date(), doctor, user, specialization);
        Visit visit2 = new Visit("3", new ISO8601DateFormat().parse("2017-01-01T00:00:00.000+0000"), doctor, null, specialization);
        visitRepository.save(visit1);
        visitRepository.save(visit2);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/visits?doctorId=10")
                        .with(SecurityMockMvcRequestPostProcessors.user("admin")))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content()
                        .json("[{'id':'3','date':'2017-01-01T00:00:00.000+0000'}]"));
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
                        .with(SecurityMockMvcRequestPostProcessors.user("admin")))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());

        Assertions.assertThat(visitRepository.findOne("1").getUser().getUsername()).isEqualTo(user.getUsername());
    }
}
