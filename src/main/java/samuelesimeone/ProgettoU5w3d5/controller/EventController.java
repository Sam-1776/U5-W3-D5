package samuelesimeone.ProgettoU5w3d5.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import samuelesimeone.ProgettoU5w3d5.dto.EventDTO;
import samuelesimeone.ProgettoU5w3d5.entities.Event;
import samuelesimeone.ProgettoU5w3d5.exceptions.BadRequestException;
import samuelesimeone.ProgettoU5w3d5.service.EventService;

import java.util.UUID;

@RestController
@RequestMapping("/event")
public class EventController {
    @Autowired
    EventService eventService;

    @GetMapping
    public Page<Event> getAll(@RequestParam(defaultValue = "0") int page,
                             @RequestParam(defaultValue = "10") int size,
                             @RequestParam(defaultValue = "id") String order) {
        return this.eventService.getEvent(page, size, order);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('ORGANIZZATORE')")
    public Event saveEvent(@RequestBody @Validated EventDTO body, BindingResult validation){
        if (validation.hasErrors()){
            throw new BadRequestException(validation.getAllErrors());
        }
        return this.eventService.save(body);
    }

    @GetMapping("/{id}")
    public Event getUserById(@PathVariable UUID id) {
        return this.eventService.findById(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ORGANIZZATORE')")
    public Event uploadUser(@PathVariable UUID id, @RequestBody EventDTO eventUp) {
        return this.eventService.update(id, eventUp);
    }


    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ORGANIZZATORE')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable UUID id) {
        this.eventService.delete(id);
    }
}
