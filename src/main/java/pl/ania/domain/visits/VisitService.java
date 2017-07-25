package pl.ania.domain.visits;

import org.springframework.stereotype.Component;
import pl.ania.domain.doctors.DoctorService;

import java.util.Date;
import java.util.UUID;

@Component
public class VisitService {

    private VisitRepository visitRepository;
    private DoctorService doctorService;

    public VisitService(VisitRepository visitRepository, DoctorService doctorService) {
        this.visitRepository = visitRepository;
        this.doctorService = doctorService;
    }

    public void addVisit(Date dateOfVisit, String doctorId){
        visitRepository.save(new Visit(UUID.randomUUID().toString(), dateOfVisit, doctorService.findById(doctorId)));
    }

    public void deleteVisit(String id){
        visitRepository.delete(id);
    }

}
