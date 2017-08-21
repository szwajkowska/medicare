package pl.ania.domain.visits;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.ania.security.User;

import java.util.Date;
import java.util.List;

public interface VisitRepository extends JpaRepository<Visit, String> {
    Visit findByDateOfVisitAndDoctorId(Date dateOfVisit, String doctorId);
    List<Visit> findByDoctorIdAndTaken(String doctorId, boolean taken);
    List<Visit> findByUser(User user);
}
