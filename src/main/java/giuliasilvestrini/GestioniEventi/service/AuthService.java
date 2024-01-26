package giuliasilvestrini.GestioniEventi.service;

import giuliasilvestrini.GestioniEventi.entities.User;
import giuliasilvestrini.GestioniEventi.entities.enums.Role;
import giuliasilvestrini.GestioniEventi.exceptions.BadRequestException;
import giuliasilvestrini.GestioniEventi.exceptions.UnauthorizedException;
import giuliasilvestrini.GestioniEventi.payloads.NewUserDTO;
import giuliasilvestrini.GestioniEventi.payloads.UserLoginDTO;
import giuliasilvestrini.GestioniEventi.repositories.UserDAO;
import giuliasilvestrini.GestioniEventi.security.JWTTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private UserService userService;
    @Autowired
    private UserDAO userDAO;
    @Autowired
    private JWTTools jwtTools;
    @Autowired
    private PasswordEncoder bcrypt;


    public User save(NewUserDTO body) {
        userDAO.findByEmail(body.email()).ifPresent(user -> {
            throw new BadRequestException("L'email " + user.getEmail() + " è già in uso!");
        });
        User newUser = new User();
        newUser.setUsername(body.username());
        newUser.setFullname(body.fullname());
        newUser.setEmail(body.email());
        newUser.setPassword(bcrypt.encode(body.password()));
        newUser.setRole(Role.USER);
        return userDAO.save(newUser);
    }

    public String authenticateUser(UserLoginDTO body) {
        User user = userService.findByEmail(body.email());

        if (bcrypt.matches(body.password(), user.getPassword())) {
            return jwtTools.createToken(user);
        } else {
            throw new UnauthorizedException("Credenziali non valide!");
        }
    }

}
