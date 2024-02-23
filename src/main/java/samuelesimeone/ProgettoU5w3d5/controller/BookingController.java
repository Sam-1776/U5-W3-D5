package samuelesimeone.ProgettoU5w3d5.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import samuelesimeone.ProgettoU5w3d5.dto.BookingDTO;
import samuelesimeone.ProgettoU5w3d5.entities.Booking;
import samuelesimeone.ProgettoU5w3d5.service.BookingService;

@RestController
@RequestMapping("/booking")
public class BookingController {

    @Autowired
    BookingService bookingService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Booking saveBooking(@RequestBody BookingDTO body) throws Exception {
        return this.bookingService.saveBooking(body);
    }
}
