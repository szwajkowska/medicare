package pl.ania.controllers;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import pl.ania.domain.doctors.DoctorService;
import pl.ania.domain.visits.VisitService;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/visits")
public class VisitsController {

    private DoctorService doctorService;
    private VisitService visitService;

    public VisitsController(DoctorService doctorService, VisitService visitService) {
        this.doctorService = doctorService;
        this.visitService = visitService;
    }

    @ResponseBody//response bo zwraca odpowiedź
    @GetMapping
    List<VisitResponse> findVisitsByDoctorId(@RequestParam String doctorId) {//request bo przychodzi string z przeglądarki
        return visitService.findAvailableVisitsByDoctorId(doctorId);
    }

    @ResponseBody
    @PostMapping
    void setAppointment(@RequestParam String visitId, Principal principal) {
        visitService.setVisitTaken(visitId, principal.getName());
    }




}
