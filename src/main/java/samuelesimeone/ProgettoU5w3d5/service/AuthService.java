package samuelesimeone.ProgettoU5w3d5.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import samuelesimeone.ProgettoU5w3d5.dao.UserDAO;
import samuelesimeone.ProgettoU5w3d5.dto.UserDTO;
import samuelesimeone.ProgettoU5w3d5.dto.UserLoginDTO;
import samuelesimeone.ProgettoU5w3d5.entities.User;
import samuelesimeone.ProgettoU5w3d5.exceptions.BadRequestException;
import samuelesimeone.ProgettoU5w3d5.exceptions.UnauthorizedExeption;
import samuelesimeone.ProgettoU5w3d5.security.JWTTools;

import java.time.LocalDate;

@Service
public class AuthService {
    @Autowired
    private UserService userService;

    @Autowired
    JWTTools jwtTools;

    @Autowired
    private PasswordEncoder bcrypt;

    @Autowired
    private UserDAO userDAO;


    public String authUserAndGenerateToken(UserLoginDTO body) throws UnauthorizedExeption {
        User user = userService.findByEmail(body.email());
        if (bcrypt.matches(body.password(), user.getPassword())){
            return jwtTools.createToken(user);
        }else {
            throw new UnauthorizedExeption("Credenziali sbagliate");
        }
    }

    public User save(UserDTO user) {
        userDAO.findByEmail(user.email()).ifPresent(newUser ->{
            throw new BadRequestException("L'email inserita è già in uso");
        });
        User newUser = new User(user.name(), user.surname(), LocalDate.parse(user.age()),user.email(), bcrypt.encode(user.password()));

        User savedUser = userDAO.save(newUser);
        return savedUser;
    }
}
