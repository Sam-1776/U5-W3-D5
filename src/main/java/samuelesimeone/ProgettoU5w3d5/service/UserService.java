package samuelesimeone.ProgettoU5w3d5.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import samuelesimeone.ProgettoU5w3d5.dao.UserDAO;
import samuelesimeone.ProgettoU5w3d5.entities.User;
import samuelesimeone.ProgettoU5w3d5.exceptions.NotFoundException;

import java.util.UUID;

@Service
public class UserService {
    @Autowired
    private UserDAO userDAO;

    public Page<User> getUsers(int pageN, int pageS, String OrderBy) {
        if (pageS > 20) pageS = 20;
        Pageable pageable = PageRequest.of(pageN, pageS, Sort.by(OrderBy));
        return userDAO.findAll(pageable);
    }

    public User findById(UUID id) {
        return userDAO.findById(id).orElseThrow(()-> new NotFoundException(id));
    }


    public User findByEmail(String email){
        return userDAO.findByEmail(email).orElseThrow(() -> new NotFoundException("Email "+ email + " non trovata"));
    }
}
