package giuliasilvestrini.GestioniEventi.controller;

import giuliasilvestrini.GestioniEventi.entities.User;
import giuliasilvestrini.GestioniEventi.service.AuthService;
import giuliasilvestrini.GestioniEventi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;


@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private AuthService authService;


    @GetMapping("")
    @PreAuthorize("hasAuthority('ADMIN')")
// solo admin può vedere tutti gli utenti
    public Page<User> getUsers(@RequestParam(defaultValue = "0") int page,
                               @RequestParam(defaultValue = "10") int size) {
        return userService.getUsers(page, size);
    }

    // /me endpoints
    @GetMapping("/me")
    public User getProfile(@AuthenticationPrincipal User currentUser) {
        return currentUser;
    }


    @PutMapping("/me")
    public User getMeAndUpdate(@AuthenticationPrincipal User currentUser, @RequestBody User body) {
        return userService.findByIdAndUpdate(currentUser.getId(), body);
    }

    @DeleteMapping("/me")

    public void getMeAnDelete(@AuthenticationPrincipal User currentUser) {
        userService.findByIdAndDelete(currentUser.getId());
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public User editUser(@PathVariable UUID id,@RequestBody User modifiedpayload) {
        return userService.findByIdAndUpdate(id, modifiedpayload);
    }

    // per sviluppo

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")

    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void getUserByIdAndDelete(@PathVariable UUID id) {
        userService.findByIdAndDelete(id);
    }


}

