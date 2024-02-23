package samuelesimeone.ProgettoU5w3d5.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import samuelesimeone.ProgettoU5w3d5.dto.LoginDTO;
import samuelesimeone.ProgettoU5w3d5.dto.UserDTO;
import samuelesimeone.ProgettoU5w3d5.dto.UserLoginDTO;
import samuelesimeone.ProgettoU5w3d5.entities.User;
import samuelesimeone.ProgettoU5w3d5.exceptions.BadRequestException;
import samuelesimeone.ProgettoU5w3d5.service.AuthService;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    AuthService authService;

    @PostMapping("/login")
    public LoginDTO login(@RequestBody UserLoginDTO body) {
        return new LoginDTO(authService.authUserAndGenerateToken(body));
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public User save(@RequestBody @Validated UserDTO body, BindingResult validation) {
        if (validation.hasErrors()){
            throw new BadRequestException(validation.getAllErrors());
        }
        return this.authService.save(body);
    }
}
