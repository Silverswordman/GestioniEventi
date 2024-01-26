package giuliasilvestrini.GestioniEventi.repositories;

import giuliasilvestrini.GestioniEventi.entities.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EventDAO extends JpaRepository<Event, UUID> {
}
