package pl.ania.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import pl.ania.domain.Specialization;
import pl.ania.domain.SpecializationList;
import pl.ania.domain.SpecializationModel;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final SpecializationList specializationList;

    public AdminController(SpecializationList specializationList) {
        this.specializationList = specializationList;
    }

    @GetMapping
    String showAdminPage(ModelMap model){
        model.put("specializations", specializationList.showAllSpecializations());
        return "adminPage";
    }

    @PostMapping
    String addSpecialization(@ModelAttribute SpecializationModel specializationModel, ModelMap model){
        specializationList.addSpecialization(specializationModel.getSpecializationName());
        model.put("specializations", specializationList.showAllSpecializations());
        return "adminPage";
    }

    @DeleteMapping
    String deleteSpecialization(String specializationName, ModelMap model){
        specializationList.deleteSpecialization(specializationName);
        model.put("specializations", specializationList.showAllSpecializations());
        return "redirect:/adminPage";
    }

}
