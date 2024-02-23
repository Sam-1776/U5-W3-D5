package samuelesimeone.ProgettoU5w3d5.entities;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.UUID;

@Entity
@Table(name = "bookings")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Booking {
    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "persona_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "evento_id")
    private Event event;

    public Booking(User user, Event event) {
        this.user = user;
        this.event = event;
    }
}
