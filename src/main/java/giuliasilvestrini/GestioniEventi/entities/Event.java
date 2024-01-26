package giuliasilvestrini.GestioniEventi.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
@ToString


public class Event {
    @Id
    @GeneratedValue
    private UUID id;
    private  String title;
    private String description;
    private LocalDate date;
    private String location;
    private int  seats;
    @JsonIgnore
    private String eventImg;


    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "event_id")
    @JsonIgnore
    private List<Reservation> reservations;



}