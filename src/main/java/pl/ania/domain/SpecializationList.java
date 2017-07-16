package pl.ania.domain;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

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

    public List<Specialization> showAllSpecializations(){

        return specializationRepository.findAll();
    }
    public Specialization findById(String id){
       return specializationRepository.findOne(id);

    }






}
