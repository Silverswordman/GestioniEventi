package giuliasilvestrini.GestioniEventi.repositories;

import giuliasilvestrini.GestioniEventi.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserDAO extends JpaRepository<User, UUID> {
}