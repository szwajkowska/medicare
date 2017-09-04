package pl.ania.domain.visits;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.ania.security.User;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface VisitRepository extends JpaRepository<Visit, String> {
    Optional<Visit> findByDateAndDoctorId(Date date, String doctorId);
    List<Visit> findByDoctorId(String doctorId);
    List<Visit> findByUser(User user);
}
