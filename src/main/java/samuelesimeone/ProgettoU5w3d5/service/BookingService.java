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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    public Booking saveBooking(BookingDTO body) throws Exception{
        User user = userService.findById(body.user());
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
}
