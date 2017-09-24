package pl.ania.api;

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

    @GetMapping
    List<DoctorResponse> doctorsBySpecializationId(@RequestParam String specializationId) {
        return specializationList.findDoctorsBySpecializationId(specializationId);
    }
}
