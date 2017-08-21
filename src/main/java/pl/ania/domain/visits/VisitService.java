package pl.ania.domain.visits;

import org.springframework.stereotype.Component;
import pl.ania.controllers.VisitResponse;
import pl.ania.domain.SpecializationList;
import pl.ania.domain.doctors.DoctorService;
import pl.ania.security.User;
import pl.ania.security.UserList;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class VisitService {

    private VisitRepository visitRepository;
    private DoctorService doctorService;
    private UserList userList;
    private SpecializationList specializationList;

    public VisitService(VisitRepository visitRepository, DoctorService doctorService, UserList userList, SpecializationList specializationList) {
        this.visitRepository = visitRepository;
        this.doctorService = doctorService;
        this.userList = userList;
        this.specializationList = specializationList;
    }

    public void addVisit(Date dateOfVisit, String doctorId, String specializationId) {
        visitRepository.save(new Visit(UUID.randomUUID().toString(),
                dateOfVisit, doctorService.findById(doctorId), specializationList.findById(specializationId)));
    }

    public void deleteVisit(String id) {
        visitRepository.delete(id);
    }

//    public boolean checkIfExists(Date dateOfVisit){
//        List<Visit> visits =  visitRepository.findAll().stream()
//                .filter(e->e.getDateOfVisit().getTime() == (dateOfVisit.getTime()))
//                .collect(Collectors.toList());
//        return !visits.isEmpty();
//    }

    public Optional<Visit> getVisit(Date dateOfVisit, String doctorId) {
        return Optional.ofNullable(visitRepository.findByDateOfVisitAndDoctorId(dateOfVisit, doctorId));
    }

    public Visit getVisitById(String id) {
        return visitRepository.findOne(id);
    }

    public void setVisitTaken(String id, String name) {
        Visit visitById = getVisitById(id);
        Optional<User> user = userList.getUser(name);
        visitRepository.save(new Visit(id, visitById.getDateOfVisit(), visitById.getDoctor(), true, user.get(), visitById.getSpecialization()));
    }

    public void setVisitCanceled(String id) {
        Visit visitById = getVisitById(id);
        visitRepository.save(new Visit(id, visitById.getDateOfVisit(), visitById.getDoctor(),
                false, null, visitById.getSpecialization()));
    }

    public List<VisitResponse> findAvailableVisitsByDoctorId(String doctorId) {
        return visitRepository.findByDoctorIdAndTaken(doctorId, false)
                .stream()
                .map(d -> new VisitResponse(d.getId(), d.getDateOfVisit()))
                .collect(Collectors.toList());
    }

    public List<Visit> findVisitsForUser(String username){
        User user = userList.getUser(username).get();
       return visitRepository.findByUser(user);
    }
}
