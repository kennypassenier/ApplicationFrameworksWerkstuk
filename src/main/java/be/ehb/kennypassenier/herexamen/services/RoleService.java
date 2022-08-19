package be.ehb.kennypassenier.herexamen.services;

import be.ehb.kennypassenier.herexamen.entities.Role;
import be.ehb.kennypassenier.herexamen.repos.RoleDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {
    // Zorgt ervoor dat de RoleDao automatisch wordt ingeladen
    @Autowired
    private RoleDao roleDao;

    public Role createNewRole(Role role){
        // De rol die we opslaan wordt door de return statement ook terug aan ons meegegeven
        return roleDao.save(role);
    }
}
