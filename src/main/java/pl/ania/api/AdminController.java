package pl.ania.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
class AdminController {

    @GetMapping
    String showAdminPage(){
        return "admin";
    }
}
