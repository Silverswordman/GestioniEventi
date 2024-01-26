package giuliasilvestrini.GestioniEventi.service;

import giuliasilvestrini.GestioniEventi.entities.Event;
import giuliasilvestrini.GestioniEventi.exceptions.BadRequestException;
import giuliasilvestrini.GestioniEventi.exceptions.NotFoundException;
import giuliasilvestrini.GestioniEventi.payloads.NewEventDTO;
import giuliasilvestrini.GestioniEventi.repositories.EventDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@Service
public class EventService {
    @Autowired
    private EventDAO eventDAO;

//    @Autowired
//    private Cloudinary cloudinaryUploader;


    public Page<Event> getEvents(int page, int size) {

        Pageable pageable = PageRequest.of(page, size);

        return eventDAO.findAll(pageable);
    }

    public Event findById(UUID id) {
        return eventDAO.findById(id).orElseThrow(() -> new NotFoundException(String.valueOf(id)));
    }


    public Event save(NewEventDTO body) {
        Optional<Event> existingEvent = eventDAO.findByTitleAndDate(body.title(), body.date());
        if (existingEvent.isPresent()) {
            throw new BadRequestException("Un evento con lo stesso titolo e data è già inserito!");
        }

        // Altrimenti, crea un nuovo evento
        Event newEvent = new Event();
        newEvent.setTitle(body.title());
        newEvent.setDescription(body.description());
        newEvent.setDate(body.date());
        newEvent.setEventImg(body.eventImg());
        newEvent.setSeats(body.seats());
        newEvent.setLocation(body.location());

        return eventDAO.save(newEvent);
    }


    public Event findByIdAndUpdate(UUID id, Event body) {
        Event found = this.findById(id);
        found.setTitle(body.getTitle());
        found.setDescription(body.getDescription());
        found.setDate(body.getDate());
        found.setEventImg(body.getEventImg());
        found.setSeats(body.getSeats());

        return eventDAO.save(found);
    }

    public void findByIdAndDelete(UUID id) {
        Event eventFound = this.findById(id);
        eventDAO.delete(eventFound);
    }

    public Event findByTitleAndDate(String title, LocalDate date) {
        return eventDAO.findByTitleAndDate(title, date)
                .orElseThrow(() -> new NotFoundException("Evento con questo " + title + " non trovato per questa data: " + date));

    }
//    public String uploadImg(MultipartFile file) throws IOException {
//        String url = (String) cloudinaryUploader.uploader()
//                .upload(file.getBytes(), ObjectUtils.emptyMap())
//                .get("url");
//        return url;
//    }


}

