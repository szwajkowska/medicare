package pl.ania.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import pl.ania.domain.SpecializationList;

import java.util.List;

@RestController
@RequestMapping("doctors")
public class DoctorsController {

    private SpecializationList specializationList;

    public DoctorsController(SpecializationList specializationList) {
        this.specializationList = specializationList;
    }

    @ResponseBody//response bo zwraca odpowiedź
    @GetMapping
    List<DoctorResponse> doctorsBySpecializationId(@RequestParam String specializationId) {//request bo przychodzi string z przeglądarki
        return specializationList.findDoctorsBySpecializationId(specializationId);
    }
    //czy metoda findDoctors... powinna być w specializationList czy w doctorService?
}
