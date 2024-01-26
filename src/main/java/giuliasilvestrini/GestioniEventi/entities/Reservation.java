package giuliasilvestrini.GestioniEventi.entities;

import giuliasilvestrini.GestioniEventi.entities.User;
import giuliasilvestrini.GestioniEventi.entities.enums.Event;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.UUID;

@Getter
@Setter
@Entity
@ToString

public class Reservation {

    @Id
    @GeneratedValue
    private UUID id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;


}
