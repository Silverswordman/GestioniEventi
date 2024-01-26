package giuliasilvestrini.GestioniEventi.controller;

import giuliasilvestrini.GestioniEventi.entities.User;
import giuliasilvestrini.GestioniEventi.exceptions.BadRequestException;
import giuliasilvestrini.GestioniEventi.payloads.NewUserDTO;
import giuliasilvestrini.GestioniEventi.payloads.NewUserResponseDTO;
import giuliasilvestrini.GestioniEventi.payloads.UserLoginDTO;
import giuliasilvestrini.GestioniEventi.payloads.UserLoginResponseDTO;
import giuliasilvestrini.GestioniEventi.service.AuthService;
import giuliasilvestrini.GestioniEventi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    AuthService authService;
    @Autowired
    UserService userService;

    @PostMapping("/login")
    public UserLoginResponseDTO login(@RequestBody UserLoginDTO body) {
        String accessToken = authService.authenticateUser(body);

        return new UserLoginResponseDTO(accessToken);
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public NewUserResponseDTO createUser(@RequestBody @Validated NewUserDTO newUserPayload, BindingResult validation) {
        System.out.println(validation);
        if (validation.hasErrors()) {
            System.out.println(validation.getAllErrors());
            throw new BadRequestException("Ci sono errori nel payload!");
        } else {
            User newUser = authService.save(newUserPayload);
            return new NewUserResponseDTO(newUser.getId(), newUser.getUsername(), newUser.getFullname());
        }
    }
}
