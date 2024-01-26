package giuliasilvestrini.GestioniEventi.controller;

import giuliasilvestrini.GestioniEventi.entities.Reservation;
import giuliasilvestrini.GestioniEventi.payloads.ReservationDTO;
import giuliasilvestrini.GestioniEventi.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reservations")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @PostMapping("")
    @PreAuthorize("hasAuthority('USER')")
    @ResponseStatus(HttpStatus.CREATED)
    public Reservation saveReservation(@RequestBody ReservationDTO body) {
        return reservationService.save(body);
    }


}