package pl.ania.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import pl.ania.domain.SpecializationList;
import pl.ania.domain.doctors.DoctorService;

@Controller
@RequestMapping("admin/doctor")
public class AdminDoctorController {

    private DoctorService doctorService;
    private SpecializationList specializationList;

    AdminDoctorController(DoctorService doctorService, SpecializationList specializationList) {
        this.doctorService = doctorService;
        this.specializationList = specializationList;
    }

    @GetMapping
    String showAdminDoctorPage(ModelMap model) {
        model.put("specializations", specializationList.findAllSpecializations());
        return "admin_doctor";
    }

    @PostMapping
    String addDoctor(@ModelAttribute DoctorModel doctorModel, ModelMap modelMap) {
        doctorService.addDoctor(doctorModel.getFirstName(), doctorModel.getLastName(),
                doctorModel.getSpecializationId());
        modelMap.put("specializations", specializationList.findAllSpecializations());
        modelMap.put("firstName", doctorModel.getFirstName());
        modelMap.put("lastName", doctorModel.getLastName());
        modelMap.put("specializationName",
                specializationList.findById(doctorModel.getSpecializationId()).getSpecializationName());
        return "admin_doctor";
    }

    @DeleteMapping(path = "/{id}")
    String deleteDoctor(@PathVariable String id, ModelMap model) {
        doctorService.deleteDoctor(id);
        model.put("specializations", specializationList.findAllSpecializations());
        model.put("doctorDeleted", true);
        return "admin_doctor"; //zmieniłam na admin-doctor, usunęłam doctorModel
    }

}
