package giuliasilvestrini.GestioniEventi.repositories;

import giuliasilvestrini.GestioniEventi.entities.Event;
import giuliasilvestrini.GestioniEventi.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

public interface EventDAO extends JpaRepository<Event, UUID> {
    Optional<Event> findByTitleAndDate(String title, LocalDate date);


}
