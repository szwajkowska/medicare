package pl.ania.domain.doctors;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import pl.ania.domain.Specialization;
import pl.ania.domain.SpecializationList;

import java.util.Arrays;
import java.util.List;

public class DoctorServiceTest {

    @Mock
    private DoctorService doctorService;

    @Mock
    private DoctorRepository doctorRepository;

    @Mock
    private SpecializationList specializationList;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        doctorService = new DoctorService(doctorRepository, specializationList);
    }

    @Test
    public void shouldShowDoctors(){
        //given
        Specialization specialization = new Specialization("id", "z");
        Doctor doctor1 = new Doctor("x", "x", "1", Arrays.asList(specialization));
        Doctor doctor2 = new Doctor("y", "Y", "2", Arrays.asList(specialization));
        Mockito.when(doctorRepository.findAll()).thenReturn(Arrays.asList(doctor1, doctor2));

        //when
        List<Doctor> doctors = doctorService.showDoctors(Arrays.asList(specialization));

        //then
        Assertions.assertThat(doctors).hasSize(2);
        Assertions.assertThat(doctors.get(0).getFirstName()).isEqualTo("x");
        Assertions.assertThat(doctors.get(1).getFirstName()).isEqualTo("y");

    }
}
