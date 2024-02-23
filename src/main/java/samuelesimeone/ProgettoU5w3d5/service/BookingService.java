package samuelesimeone.ProgettoU5w3d5.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import samuelesimeone.ProgettoU5w3d5.dao.BookingDAO;
import samuelesimeone.ProgettoU5w3d5.dao.EventDAO;
import samuelesimeone.ProgettoU5w3d5.dao.UserDAO;
import samuelesimeone.ProgettoU5w3d5.dto.BookingDTO;
import samuelesimeone.ProgettoU5w3d5.entities.Availability;
import samuelesimeone.ProgettoU5w3d5.entities.Booking;
import samuelesimeone.ProgettoU5w3d5.entities.Event;
import samuelesimeone.ProgettoU5w3d5.entities.User;
import samuelesimeone.ProgettoU5w3d5.exceptions.BadRequestException;
import samuelesimeone.ProgettoU5w3d5.exceptions.NotFoundException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
public class BookingService {
    @Autowired
    BookingDAO bookingDAO;

    @Autowired
    UserService userService;

    @Autowired
    EventService eventService;

    @Autowired
    EventDAO eventDAO;

    @Autowired
    UserDAO userDAO;

    public Booking saveBooking(UUID userId, BookingDTO body) throws Exception{
        User user = userService.findById(userId);
        Event event = eventService.findById(body.event());
        Booking newBooking = new Booking(user, event);
        if (event.getBookings().size() == event.getNMax() || event.getAvailability().equals(Availability.UNAVAILABLE)){
            event.setAvailability(Availability.UNAVAILABLE);
            eventDAO.save(event);
            throw new Exception("L'evento non è disponibile");
        }
        List<Booking> bookingListUser = new ArrayList<>();
        bookingListUser.add(newBooking);
        user.setBookings(bookingListUser);
        List<Booking> bookingListEvent = new ArrayList<>();
        bookingListEvent.add(newBooking);
        event.setBookings(bookingListEvent);
        if (event.getBookings().size() == event.getNMax()){
            event.setAvailability(Availability.UNAVAILABLE);
        }
        userDAO.save(user);
        eventDAO.save(event);
        return bookingDAO.save(newBooking);
    }

    public List<Booking> findByUser(UUID id){
        User found = userService.findById(id);
        return bookingDAO.findByUser(found);
    }

    public Booking findById(UUID id) {
        return bookingDAO.findById(id).orElseThrow(()-> new NotFoundException(id));
    }

    public void delete(UUID UserID,UUID BookingId) throws NullPointerException {
        Booking found = this.findById(BookingId);
        if (UserID.equals(found.getUser().getId())){
        Event event = found.getEvent();
        bookingDAO.delete(found);
        if (event.getAvailability().equals(Availability.UNAVAILABLE) && event.getBookings().size() < event.getNMax()){
            event.setAvailability(Availability.AVAILABLE);
            eventDAO.save(event);
        }
        }else {
            throw new NullPointerException("La prenotazione che stai cancellando non è tua");
        }
    }
}
