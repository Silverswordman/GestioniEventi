package giuliasilvestrini.GestioniEventi.controller;

import giuliasilvestrini.GestioniEventi.entities.Event;
import giuliasilvestrini.GestioniEventi.payloads.NewEventDTO;
import giuliasilvestrini.GestioniEventi.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/events")
public class EventController {


    @Autowired
    private EventService eventService;


    @GetMapping("")

    public Page<Event> getEvents(@RequestParam(defaultValue = "0") int page,
                                 @RequestParam(defaultValue = "10") int size) {
        return eventService.getEvents(page, size);
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('ADMIN')")
    public Event saveEvent(@RequestBody NewEventDTO body) {
        return eventService.save(body);
    }
}