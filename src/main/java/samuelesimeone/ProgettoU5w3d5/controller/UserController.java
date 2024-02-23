package samuelesimeone.ProgettoU5w3d5.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import samuelesimeone.ProgettoU5w3d5.entities.User;
import samuelesimeone.ProgettoU5w3d5.service.UserService;

import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping
    public Page<User> getAll(@RequestParam(defaultValue = "0") int page,
                             @RequestParam(defaultValue = "10") int size,
                             @RequestParam(defaultValue = "id") String order) {
        return this.userService.getUsers(page, size, order);
    }

    @GetMapping("/profile")
    public User getProfile(@AuthenticationPrincipal User currentUser){
        return currentUser;
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable UUID id) {
        return this.userService.findById(id);
    }
}
