package pl.ania.domain;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SpecializationList {

    private SpecializationRepository specializationRepository;

    SpecializationList(SpecializationRepository specializationRepository) {
        this.specializationRepository = specializationRepository;
    }


    public void addSpecialization(String specializationName){
        specializationRepository.save(new Specialization(specializationName));
    }

    public void deleteSpecialization(String specializationName){
        specializationRepository.delete(specializationName);
    }

    public List<Specialization> showAllSpecializations(){
        return specializationRepository.findAll();
    }





}
