package pl.ania.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import pl.ania.domain.visits.VisitService;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/visits")
public class VisitsController {

    private VisitService visitService;

    public VisitsController(VisitService visitService) {
        this.visitService = visitService;
    }

    @GetMapping
    List<VisitResponse> findVisitsByDoctorId(@RequestParam String doctorId) {
        return visitService.findAvailableVisitsByDoctorId(doctorId);
    }

    @ResponseBody
    @PostMapping
    void setAppointment(@RequestParam String visitId, Principal principal) {
        visitService.setVisitTaken(visitId, principal.getName());
    }




}
