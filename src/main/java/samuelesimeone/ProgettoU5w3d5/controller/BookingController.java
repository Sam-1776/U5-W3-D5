package samuelesimeone.ProgettoU5w3d5.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import samuelesimeone.ProgettoU5w3d5.dto.BookingDTO;
import samuelesimeone.ProgettoU5w3d5.entities.Booking;
import samuelesimeone.ProgettoU5w3d5.entities.User;
import samuelesimeone.ProgettoU5w3d5.exceptions.BadRequestException;
import samuelesimeone.ProgettoU5w3d5.service.BookingService;

import java.util.List;
import java.util.UUID;

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

    @GetMapping("/{UserID}")
    public List<Booking> foundByUser(@PathVariable UUID UserID){
        return this.bookingService.findByUser(UserID);
    }

    @DeleteMapping("/profile/{BookingID}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBooking(@AuthenticationPrincipal User currentUser, @PathVariable UUID BookingID){
        this.bookingService.delete(currentUser.getId(), BookingID);
    }
}
