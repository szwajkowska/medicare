package pl.ania.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.ania.security.UserList;
import pl.ania.security.UserModel;
import pl.ania.security.UserValidator;

import javax.validation.Valid;

@Controller
@RequestMapping("/signIn")
class SignInController {

    private final UserList userList;
    private final UserValidator userValidator;

    SignInController(UserList userList, UserValidator userValidator) {
        this.userList = userList;
        this.userValidator = userValidator;
    }

    @GetMapping
    String signIn(UserModel userModel){
        return "signIn";
    }

    @PostMapping
    String createNewUser(@Valid @ModelAttribute UserModel userModel, BindingResult result){
        userValidator.validate(userModel, result);
        if (result.hasErrors()){
            return "signIn";
        }
        userList.addUser(userModel.getUsername(), userModel.getPassword(), userModel.getEmail());
        return "redirect:/login?singedIn";
    }


}
