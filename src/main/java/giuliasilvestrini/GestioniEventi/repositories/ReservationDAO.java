package giuliasilvestrini.GestioniEventi.repositories;

import giuliasilvestrini.GestioniEventi.entities.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ReservationDAO extends JpaRepository<Reservation, UUID> {


}
