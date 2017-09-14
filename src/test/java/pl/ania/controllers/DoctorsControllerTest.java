package pl.ania.controllers;


import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import pl.ania.domain.Specialization;
import pl.ania.domain.doctors.Doctor;

import java.util.Arrays;

public class DoctorsControllerTest extends ControllerTest{

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
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content()
                        .json("[{'id':'2','firstName':'Jan','lastName':'Kowalski'}," +
                                "{'id':'3','firstName':'Adam','lastName':'Nowak'}]"));
    }
}
