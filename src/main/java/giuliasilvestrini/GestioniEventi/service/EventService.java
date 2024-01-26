package giuliasilvestrini.GestioniEventi.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import giuliasilvestrini.GestioniEventi.entities.Event;
import giuliasilvestrini.GestioniEventi.exceptions.NotFoundException;
import giuliasilvestrini.GestioniEventi.payloads.NewEventDTO;
import giuliasilvestrini.GestioniEventi.repositories.EventDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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

        Event newEvent = new Event();
        newEvent.setTitle(body.title());
        newEvent.setDescription(body.description());
        newEvent.setDate(body.date());
        newEvent.setEventImg(body.eventImg());
        newEvent.setSeats(body.seats());

        return eventDAO.save(newEvent);
    }

    public Event findByIdAndUpdate(UUID id, NewEventDTO body) {
        Event found = this.findById(id);
        found.setTitle(body.title());
        found.setDescription(body.description());
        found.setDate(body.date());
        found.setEventImg(body.eventImg());
        found.setSeats(body.seats());

        return eventDAO.save(found);
    }

    public void findByIdAndDelete(UUID id) {
        Event eventFound = this.findById(id);
        eventDAO.delete(eventFound);
    }

//    public String uploadImg(MultipartFile file) throws IOException {
//        String url = (String) cloudinaryUploader.uploader()
//                .upload(file.getBytes(), ObjectUtils.emptyMap())
//                .get("url");
//        return url;
//    }


}

