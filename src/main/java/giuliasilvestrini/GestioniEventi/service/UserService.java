package giuliasilvestrini.GestioniEventi.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import giuliasilvestrini.GestioniEventi.entities.User;
import giuliasilvestrini.GestioniEventi.exceptions.NotFoundException;
import giuliasilvestrini.GestioniEventi.payloads.NewUserDTO;
import giuliasilvestrini.GestioniEventi.repositories.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Service
public class UserService {
    @Autowired
    private UserDAO userDAO;


    public Page<User> getUsers(int page, int size) {

        Pageable pageable = PageRequest.of(page, size);

        return userDAO.findAll(pageable);
    }

    public User findById(UUID id) {
        return userDAO.findById(id).orElseThrow(() -> new NotFoundException(String.valueOf(id)));
    }


    public User findByIdAndUpdate(UUID id, NewUserDTO body) {
        User found = this.findById(id);
        found.setUsername(body.username());
        found.setFullname(body.fullname());
        found.setEmail(body.email());
        found.setPassword(body.password());

        return userDAO.save(found);
    }

    public void findByIdAndDelete(UUID id) {
        User userFound = this.findById(id);
        userDAO.delete(userFound);
    }


    public User findByEmail(String email) throws NotFoundException {
        return userDAO.findByEmail(email).orElseThrow(() -> new NotFoundException("Utente con email " + email + " non esistente "));
    }

}
