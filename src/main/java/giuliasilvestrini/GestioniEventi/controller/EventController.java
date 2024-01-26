package giuliasilvestrini.GestioniEventi.controller;

import giuliasilvestrini.GestioniEventi.entities.Event;
import giuliasilvestrini.GestioniEventi.payloads.NewEventDTO;
import giuliasilvestrini.GestioniEventi.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

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
    @PreAuthorize("hasAuthority('MANAGER')")
    public Event saveEvent(@RequestBody NewEventDTO body) {
        return eventService.save(body);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('MANAGER')")
    public Event getEventandUpdate(@PathVariable UUID id, @RequestBody Event modifiedEvent) {
        return eventService.findByIdAndUpdate(id, modifiedEvent);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('MANAGER')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void getEventByIdAndDelete(@PathVariable UUID id) {
        eventService.findByIdAndDelete(id);
    }
}
