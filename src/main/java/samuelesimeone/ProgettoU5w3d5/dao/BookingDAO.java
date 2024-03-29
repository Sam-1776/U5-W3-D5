package samuelesimeone.ProgettoU5w3d5.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import samuelesimeone.ProgettoU5w3d5.entities.Booking;
import samuelesimeone.ProgettoU5w3d5.entities.User;

import java.util.List;
import java.util.UUID;

@Repository
public interface BookingDAO extends JpaRepository<Booking, UUID> {

    List<Booking> findByUser(User user);
}
