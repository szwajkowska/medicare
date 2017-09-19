package pl.ania.api;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.ania.domain.visits.Visit;
import pl.ania.domain.visits.VisitService;
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/my_visits")
public class MyVisitsController {

    private VisitService visitService;

    public MyVisitsController(VisitService visitService) {
        this.visitService = visitService;
    }

    @GetMapping
    String returnUserVisits(Principal principal, ModelMap modelMap) {
        String username = principal.getName();
        List<Visit> visits = visitService.findVisitsForUser(username);
        modelMap.put("visits", visits);
        return "my_visits";
    }

    @ResponseBody
    @PostMapping(path = "/{id}")
    void cancelVisit(@PathVariable String id) {
        visitService.setVisitCanceled(id);
    }
}
