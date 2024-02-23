package samuelesimeone.ProgettoU5w3d5.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import samuelesimeone.ProgettoU5w3d5.entities.User;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserDAO extends JpaRepository<User, UUID> {
    Optional<User> findByEmail(String email);
}
