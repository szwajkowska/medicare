package pl.ania.domain.visits;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import pl.ania.api.VisitResponse;
import pl.ania.domain.SpecializationList;
import pl.ania.domain.doctors.DoctorService;
import pl.ania.security.User;
import pl.ania.security.UserList;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class VisitServiceTest {

    @Mock
    private VisitRepository visitRepository;

    @Mock
    private DoctorService doctorService;

    @Mock
    private UserList userList;

    @Mock
    private SpecializationList specializationList;

    @Mock
    private VisitService visitService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        visitService = new VisitService(visitRepository, doctorService, userList, specializationList);
    }

    @Test
    public void shouldReturnAvailableVisitsByDoctorId() {
        //given
        Visit visit = new Visit("1", new Date(), null, null);
        Mockito.when(visitRepository.findByDoctorId("id")).thenReturn(Arrays.asList(visit));

        //when
        List<VisitResponse> visits = visitService.findAvailableVisitsByDoctorId("id");

        //then
        Assertions.assertThat(visits).hasSize(1);
    }

    @Test
    public void shouldNotReturnVisitsByDoctorId(){
        //given
        Visit visit = new Visit("1", null, null, new User("1", "Jan Kowalski", "xxx", "xxx@xxx.com"), null);
        Mockito.when(visitRepository.findByDoctorId("id")).thenReturn(Arrays.asList(visit));

        //when
        List<VisitResponse> visits = visitService.findAvailableVisitsByDoctorId("id");

        //then
        Assertions.assertThat(visits).hasSize(0);
    }

    @Test
    public void shouldMakeVisitTaken(){
        //given
        Visit visit = new Visit("id", null, null, null, null);
        Optional<User> user = Optional.of(new User("1", "name", "xxx", "xxx@xxx"));
        Mockito.when(visitRepository.findOne("id")).thenReturn(visit);
        Mockito.when(userList.getUser("name")).thenReturn(user);

        //when
        visitService.setVisitTaken("id", "name");

        //then
        ArgumentCaptor<Visit> savedCaptor = ArgumentCaptor.forClass(Visit.class);
        Mockito.verify(visitRepository).save(savedCaptor.capture());
        Assertions.assertThat(savedCaptor.getValue().getUser().getId()).isEqualTo("1");

    }
}