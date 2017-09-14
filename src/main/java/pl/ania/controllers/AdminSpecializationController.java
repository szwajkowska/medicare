package pl.ania.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
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
        model.put("specializations", specializationList.findAllSpecializations());
        return "admin_specialization";
    }

    @PostMapping
    String addSpecialization(@ModelAttribute SpecializationModel specializationModel, ModelMap model){
        specializationList.addSpecialization(specializationModel.getSpecializationName());
        model.put("specializations", specializationList.findAllSpecializations());
        model.put("specializationName", specializationModel.getSpecializationName());
        return "admin_specialization";
    }

    @DeleteMapping(path = "/{id}")
    @ResponseBody
   void deleteSpecialization(@PathVariable String id) {
        specializationList.deleteSpecialization(id);
    }
}
