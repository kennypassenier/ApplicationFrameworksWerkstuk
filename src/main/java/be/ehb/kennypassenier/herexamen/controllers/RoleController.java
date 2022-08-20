package be.ehb.kennypassenier.herexamen.controllers;

import be.ehb.kennypassenier.herexamen.entities.Role;
import be.ehb.kennypassenier.herexamen.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RoleController {

    // Automatisch inladen van de RoleService
    @Autowired
    private RoleService roleService;
    @CrossOrigin(origins = "*")
    @PostMapping({"/createNewRole"})
    public Role createNewRole(@RequestBody Role role){
        return roleService.createNewRole(role);
    }
}
