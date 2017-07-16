package pl.ania.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import pl.ania.domain.SpecializationList;
import pl.ania.domain.doctors.Doctor;
import pl.ania.domain.doctors.DoctorService;

import java.util.List;

@Controller
@RequestMapping("admin/doctor")
public class AdminDoctorController {

    private DoctorService doctorService;
    private SpecializationList specializationList;

    public AdminDoctorController(DoctorService doctorService, SpecializationList specializationList) {
        this.doctorService = doctorService;
        this.specializationList = specializationList;
    }

    @GetMapping
    String showAdminDoctorPage(ModelMap model, DoctorModel doctorModel) {
        model.put("specializations", specializationList.showAllSpecializations());
        return "admin_doctor";
    }




    @PostMapping
    String addDoctor(@ModelAttribute DoctorModel doctorModel, ModelMap modelMap) {
        doctorService.addDoctor(doctorModel.getFirstName(), doctorModel.getLastName(),
                doctorModel.getSpecializationId());
        modelMap.put("specializations", specializationList.showAllSpecializations());
        modelMap.put("firstName", doctorModel.getFirstName());
        modelMap.put("lastName", doctorModel.getLastName());
        modelMap.put("specializationName",
                specializationList.findById(doctorModel.getSpecializationId()).getSpecializationName());
        return "admin_doctor";
    }


    @DeleteMapping(path = "/{id}")
    String deleteDoctor(@PathVariable String id, ModelMap model, DoctorModel doctorModel) {
        doctorService.deleteDoctor(id);
        model.put("specializations", specializationList.showAllSpecializations());
        model.put("doctorDeleted", true);
        model.put("firstName", doctorModel.getFirstName());
        model.put("lastName", doctorModel.getLastName());
        return "admin_specialization";
    }

}
