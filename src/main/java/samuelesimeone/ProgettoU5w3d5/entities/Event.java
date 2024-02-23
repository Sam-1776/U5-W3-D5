package samuelesimeone.ProgettoU5w3d5.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "events")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Event {
    @Id
    @GeneratedValue
    private UUID id;
    private String title;
    private LocalDate date;
    private String description;
    private long nMax;
    private String location;
    @Enumerated(EnumType.STRING)
    private Availability availability;
    @JsonIgnore
    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL)
    private List<Booking> bookings;


    public Event(String title, LocalDate date, String description, long nMax, String location) {
        this.title = title;
        this.date = date;
        this.description = description;
        this.nMax = nMax;
        this.location = location;
        this.availability = Availability.AVAILABLE;
    }
}
