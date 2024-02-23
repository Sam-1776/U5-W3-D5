package samuelesimeone.ProgettoU5w3d5.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import samuelesimeone.ProgettoU5w3d5.entities.Event;

import java.util.UUID;

@Repository
public interface EventDAO extends JpaRepository<Event, UUID> {
}
