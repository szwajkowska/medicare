package pl.ania.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import pl.ania.domain.SpecializationList;

@Controller
@RequestMapping("/admin/specialization")
public class AdminSpecializationController {

    private final SpecializationList specializationList;

    public AdminSpecializationController(SpecializationList specializationList) {
        this.specializationList = specializationList;
    }

    @GetMapping
    String showAdminPage(ModelMap model){
        model.put("specializations", specializationList.showAllSpecializations());
        return "admin_specialization";
    }

    @PostMapping
    String addSpecialization(@ModelAttribute SpecializationModel specializationModel, ModelMap model){
        specializationList.addSpecialization(specializationModel.getSpecializationName());
        model.put("specializations", specializationList.showAllSpecializations());
        model.put("specializationName", specializationModel.getSpecializationName());
        return "admin_specialization";
    }

//czemu musi być ta metoda?

    @DeleteMapping(path = "/{id}")
    String deleteSpecialization(@PathVariable String id, ModelMap model) {
        specializationList.deleteSpecialization(id);
        model.put("specializations", specializationList.showAllSpecializations());
        model.put("specializationDeleted", true);
        return "admin_specialization";
    }


//    @RequestMapping(path = "/{specializationName}")
//    String showSpecialization(@PathVariable String specializationName){
//
//        return "redirect:/admin?specializationDeleted";
//    }

}
