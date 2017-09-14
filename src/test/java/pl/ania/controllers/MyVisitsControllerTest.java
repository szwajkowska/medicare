package pl.ania.controllers;

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

public class MyVisitsControllerTest extends ControllerTest{

    @Test
    public void shouldReturnUserVisit() throws Exception{
        User user = new User("1", "name", "xxx", "xxx@xxx");
        userRepository.save(user);
        Specialization specialization1 = new Specialization("10", "chirurgia");
        specializationRepository.save(specialization1);
        Specialization specialization2 = new Specialization("20", "kardiologia");
        specializationRepository.save(specialization2);
        Doctor doctor1 = new Doctor("Jan", "Kowalski", "1", Arrays.asList(specialization1));
        doctorRepository.save(doctor1);
        Doctor doctor2 = new Doctor("Adam", "Kowalski", "2", Arrays.asList(specialization2));
        doctorRepository.save(doctor2);
        Visit visit1 = new Visit("2", new Date(), doctor1, user, specialization1);
        Visit visit2 = new Visit("3", new Date(), doctor2, user, specialization2);
        visitRepository.save(visit1);
        visitRepository.save(visit2);
        Principal principal = () -> "name";

        mockMvc.perform(
                MockMvcRequestBuilders.get("/my_visits")
                        .principal(principal)
                        .with(SecurityMockMvcRequestPostProcessors.user("admin")))
                .andExpect(MockMvcResultMatchers.status().isOk());

        Assertions.assertThat(userRepository.findOne("1").getVisits()).hasSize(2);
        Assertions.assertThat(userRepository.findOne("1").getVisits().get(0).getId()).isEqualTo("2");
    }

    @Test
    public void shouldCancelVisit() throws Exception{
        Specialization specialization = new Specialization("1", "chirirgia");
        specializationRepository.save(specialization);
        Doctor doctor = new Doctor("Jan", "Kowalski", "1", Arrays.asList(specialization));
        doctorRepository.save(doctor);
        User user = new User("1", "name", "xxx", "xxx@xxx");
        userRepository.save(user);
        Visit visit = new Visit("1", new Date(), doctor, user, specialization);
        visitRepository.save(visit);

        mockMvc.perform(
                MockMvcRequestBuilders.post("/my_visits/1")
                        .with(SecurityMockMvcRequestPostProcessors.user("admin")))
                .andExpect(MockMvcResultMatchers.status().isOk());

        Assertions.assertThat(visitRepository.count()).isEqualTo(1);
        Assertions.assertThat(visitRepository.findOne("1").getUser()).isEqualTo(null);
        Assertions.assertThat(doctorRepository.count()).isEqualTo(1);
        Assertions.assertThat(specializationRepository.count()).isEqualTo(1);
    }
}
