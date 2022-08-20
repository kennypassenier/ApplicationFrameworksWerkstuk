package be.ehb.kennypassenier.herexamen.controllers;

import be.ehb.kennypassenier.herexamen.entities.User;
import be.ehb.kennypassenier.herexamen.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    // Seeder voor onze rollen en enkele users
    // In onze config hebben we ervoor gezorgd dat telkens dat de applicatie opstart
    // We onze database opnieuw recreëren aan de hand van onze entities
    @PostConstruct
    public void seedRolesAndUsers(){
        userService.seedRolesAndUser();
    }
    @CrossOrigin(origins = "*")
    @PostMapping({"/registerNewUser"})
    public User registerNewUser(@RequestBody User user){
        return userService.registerNewUser(user);
    }


    @CrossOrigin(origins = "*")
    @GetMapping({"/forAdminsOnly"})
    @PreAuthorize("hasRole('Admin')")
    public String forAdmin(){
        return "This URL is only for admins";
    }
    @CrossOrigin(origins = "*")
    @GetMapping({"/forUsersOnly"})
    @PreAuthorize("hasRole('User')")
    public String forUser(){
        return "This URL is only for users";
    }

}
