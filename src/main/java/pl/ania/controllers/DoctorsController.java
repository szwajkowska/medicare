package pl.ania.controllers;

import org.springframework.web.bind.annotation.*;
import pl.ania.domain.SpecializationList;
import pl.ania.domain.doctors.Doctor;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("doctors")
public class DoctorsController {

    private SpecializationList specializationList;

    public DoctorsController(SpecializationList specializationList) {
        this.specializationList = specializationList;
    }

    @ResponseBody//response bo zwraca odpowiedź
    @GetMapping
    List<DoctorResponse> doctorsBySpecializationId(@RequestParam String specializationId){//request bo przychodzi string z przeglądarki
        return specializationList.findDoctorsBySpecializationId(specializationId);
    }
    //czy metoda findDoctors... powinna być w specializationList czy w doctorService?
}
