package pl.ania.domain.doctors;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.ania.domain.visits.Visit;
import java.util.List;

public interface DoctorRepository extends JpaRepository<Doctor, String>{
    Doctor findByVisits(List<Visit> visits);
}
