package pl.ania.domain;

import org.springframework.stereotype.Component;

@Component
public class SpecializationList {

    private SpecializationRepository specializationRepository;

    SpecializationList(SpecializationRepository specializationRepository) {
        this.specializationRepository = specializationRepository;
    }




}
