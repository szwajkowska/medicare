package pl.ania.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.ania.domain.Specialization;
import pl.ania.domain.SpecializationList;

import java.security.Principal;

@Controller
@RequestMapping("/")
public class MainController {

    private final SpecializationList specializationList;

    public MainController(SpecializationList specializationList) {
        this.specializationList = specializationList;
    }

    @GetMapping
    String showMainPage(Principal principal, ModelMap model){
        String name = principal.getName();
        model.put("username", name);
        model.put("specializations", specializationList.showAllSpecializations());
        return "main";
    }
}
