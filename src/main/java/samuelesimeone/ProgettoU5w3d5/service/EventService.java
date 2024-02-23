package samuelesimeone.ProgettoU5w3d5.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import samuelesimeone.ProgettoU5w3d5.dao.EventDAO;
import samuelesimeone.ProgettoU5w3d5.dto.EventDTO;
import samuelesimeone.ProgettoU5w3d5.entities.Event;
import samuelesimeone.ProgettoU5w3d5.exceptions.NotFoundException;

import java.time.LocalDate;
import java.util.UUID;

@Service
public class EventService {
    @Autowired
    EventDAO eventDAO;

    public Page<Event> getEvent(int pageN, int pageS, String OrderBy) {
        if (pageS > 20) pageS = 20;
        Pageable pageable = PageRequest.of(pageN, pageS, Sort.by(OrderBy));
        return eventDAO.findAll(pageable);
    }

    public Event save(EventDTO event) {
        Event newEvent = new Event(event.title(), LocalDate.parse(event.date()), event.description(), event.nMax(), event.location());
        return eventDAO.save(newEvent);
    }

    public Event findById(UUID id) {
        return eventDAO.findById(id).orElseThrow(()-> new NotFoundException(id));
    }

    public Event update(UUID id, EventDTO eventUp) {
        Event found = this.findById(id);
        found.setAvailability(found.getAvailability());
        found.setDate(LocalDate.parse(eventUp.date()));
        found.setDescription(eventUp.description());
        found.setTitle(eventUp.title());
        found.setLocation(eventUp.location());
        found.setNMax(eventUp.nMax());
        return eventDAO.save(found);
    }

    public void delete(UUID id) {
        Event found = this.findById(id);
        eventDAO.delete(found);
    }
}
