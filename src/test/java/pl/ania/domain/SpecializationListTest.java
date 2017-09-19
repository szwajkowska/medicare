package pl.ania.domain;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import pl.ania.api.DoctorResponse;
import pl.ania.domain.doctors.Doctor;

import java.util.Arrays;
import java.util.List;

public class SpecializationListTest {

    @Mock
    private SpecializationRepository specializationRepository;

    @Mock
    private SpecializationList specializationList;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        specializationList = new SpecializationList(specializationRepository);
    }

    @Test
    public void shouldFindDoctorsBySpecializationId(){
        //given
        Doctor doctor = new Doctor("Jan", "Kowalski", "1", null);
        Specialization specialization = new Specialization("id", "name", Arrays.asList(doctor));

        Mockito.when(specializationRepository.findOne("id")).thenReturn(specialization);

        //when
        List<DoctorResponse> doctors = specializationList.findDoctorsBySpecializationId("id");

        //then
        Assertions.assertThat(doctors.get(0).getId()).isEqualTo("1");
    }
}
