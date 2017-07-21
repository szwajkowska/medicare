package pl.ania.domain;

import org.springframework.stereotype.Component;
import pl.ania.controllers.DoctorResponse;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class SpecializationList {

    private SpecializationRepository specializationRepository;

    SpecializationList(SpecializationRepository specializationRepository) {
        this.specializationRepository = specializationRepository;
    }

    public void addSpecialization(String specializationName){
        specializationRepository.save(new Specialization(UUID.randomUUID().toString(), specializationName));
    }

    public void deleteSpecialization(String id){
        specializationRepository.delete(id);
    }

    public List<Specialization> findAllSpecializations(){

        return specializationRepository.findAll();
    }
    public Specialization findById(String id){
       return specializationRepository.findOne(id);

    }

    public List<DoctorResponse> findDoctorsBySpecializationId(String specializationId) {
        return findById(specializationId).getDoctors()
                .stream()
                .map(d -> new DoctorResponse(d.getId(), d.getFirstName(), d.getLastName()))
                .collect(Collectors.toList());
    }
}
