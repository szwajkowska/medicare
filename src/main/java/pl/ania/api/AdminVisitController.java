package pl.ania.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.ania.domain.SpecializationList;
import pl.ania.domain.visits.VisitService;

import javax.validation.Valid;

@Controller
@RequestMapping("/admin/visit")
public class AdminVisitController {

    private static final Logger logger = LoggerFactory.getLogger(AdminVisitController.class);

    private SpecializationList specializationList;
    private VisitService visitService;

    public AdminVisitController(SpecializationList specializationList, VisitService visitService) {
        this.specializationList = specializationList;
        this.visitService = visitService;
    }

    @GetMapping
    String showAdminVisitPage(ModelMap model) {
        model.put("specializations", specializationList.findAllSpecializations());
        return "admin_visit";
    }

    @PostMapping
    String addVisit(@Valid @ModelAttribute VisitModel visitModel, BindingResult result, ModelMap modelMap) {
        if (result.hasErrors()) {
            String errors = "";
            if (result.getFieldError("doctorId") != null) {
                logger.info("Errors found {}", result);
                errors = "doctorId_error";
            }
            if (!errors.equals("")) {
                errors += "&";
            }
            if (result.getFieldError("date") != null) {
                errors += "dateOfVisit_error";
            }
            return "redirect:/admin/visit?" + errors;
        }
        if (visitService.getVisit(visitModel.getDate(), visitModel.getDoctorId()).isPresent()){
            return "redirect:/admin/visit?visitEngaged";
        }

        logger.info("new Visit requested for date {}", visitModel.getDate());
        visitService.addVisit(visitModel.getDate(), visitModel.getDoctorId(), visitModel.getSpecializationId());
        modelMap.put("specializations", specializationList.findAllSpecializations());
        modelMap.put("date", visitModel.getDate());
        return "admin_visit";
    }

    @DeleteMapping(path = "/{id}")
    @ResponseBody
    void deleteVisit(@PathVariable String id) {
        visitService.deleteVisit(id);
    }
}
