package pl.ania.domain.doctors;

import org.springframework.stereotype.Component;
import pl.ania.domain.Specialization;
import pl.ania.domain.SpecializationList;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Component
public class DoctorService {

    private DoctorRepository doctorRepository;
    private SpecializationList specializationList;


    DoctorService(DoctorRepository doctorRepository, SpecializationList specializationList) {
        this.doctorRepository = doctorRepository;
        this.specializationList = specializationList;
    }

    public void addDoctor(String firstName, String lastName, String specializationId){
        List<Specialization> specializations = new ArrayList<>();
        specializations.add(specializationList.findById(specializationId));
        doctorRepository.save(new Doctor(firstName, lastName, UUID.randomUUID().toString(), specializations));
    }

    public void deleteDoctor(String id){
        doctorRepository.delete(id);
    }

    public List<Doctor> showDoctors(List<Specialization> specializations){
        return StreamSupport.stream(doctorRepository.findAll().spliterator(), false)
                .filter(e->e.getSpecializations().equals(specializations))
                .collect(Collectors.toList());
    }


}
