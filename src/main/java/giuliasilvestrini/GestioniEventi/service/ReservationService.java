package giuliasilvestrini.GestioniEventi.service;

import giuliasilvestrini.GestioniEventi.entities.Event;
import giuliasilvestrini.GestioniEventi.entities.Reservation;
import giuliasilvestrini.GestioniEventi.entities.User;
import giuliasilvestrini.GestioniEventi.exceptions.BadRequestException;
import giuliasilvestrini.GestioniEventi.exceptions.NotFoundException;
import giuliasilvestrini.GestioniEventi.payloads.ReservationDTO;
import giuliasilvestrini.GestioniEventi.repositories.ReservationDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReservationService {

    @Autowired
    private ReservationDAO reservationDAO;

    @Autowired
    private UserService userService;

    @Autowired
    private EventService eventService;

    public Reservation save(ReservationDTO body) {
        // Trova l'utente
        User user = userService.findByEmail(body.getUserEmail());
        if (user == null) {
            throw new NotFoundException("Utente non trovato: " + body.getUserEmail());
        }

        // Trova l'evento
        Event event = eventService.findByTitleAndDate(body.getEventTitle(), body.getEventDate());
        if (event == null) {
            throw new NotFoundException("Evento non trovato  " + body.getEventTitle());
        }
        if (event.getReservations().size() >= event.getSeats()) {

            throw new BadRequestException("Numero massimo di prenotazioni raggiunto per questo evento");
        }


        Reservation reservation = new Reservation();
        reservation.setUser(user);
        reservation.setEvent(event);

        return reservationDAO.save(reservation);
    }


}