package be.ehb.kennypassenier.herexamen.services;

import be.ehb.kennypassenier.herexamen.entities.Role;
import be.ehb.kennypassenier.herexamen.entities.User;
import be.ehb.kennypassenier.herexamen.repos.RoleDao;
import be.ehb.kennypassenier.herexamen.repos.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User registerNewUser(User user){
        // Haal de rol van User uit de database
        Role role = roleDao.findById("User").get();
        Set<Role> roles = new HashSet<>();
        roles.add(role);
        // Geef elke nieuwe gebruiker deze rol
        user.setRole(roles);
        user.setPassword(getEncodedPassword(user.getPassword()));
        return userDao.save(user);
    }

    public void seedRolesAndUser(){
        // Rollen aanmaken
        Role adminRole = new Role();
        adminRole.setName("Admin");
        adminRole.setDescription("Admin role");
        roleDao.save(adminRole);

        Role userRole = new Role();
        userRole.setName("User");
        userRole.setDescription("Default role for our users");
        roleDao.save(userRole);

        // Users aanmaken
        User adminUser = new User();
        adminUser.setUserName("Admin");
        adminUser.setFirstName("Admin");
        adminUser.setLastName("Admin");
        adminUser.setCity("Brussel");
        adminUser.setPostalCode("1000");
        adminUser.setStreet("Brederodestraat");
        adminUser.setHouseNumber("16");
        adminUser.setPassword(getEncodedPassword("admin123"));
        // Rol aan user toevoegen
        Set<Role> adminRoles = new HashSet<>();
        adminRoles.add(adminRole);
        adminUser.setRole(adminRoles);
        userDao.save(adminUser);

        User normalUser = new User();
        normalUser.setUserName("Kenny");
        normalUser.setFirstName("Kenny");
        normalUser.setLastName("Passenier");
        normalUser.setCity("Kampenhout");
        normalUser.setPostalCode("1910");
        normalUser.setStreet("Daallaan");
        normalUser.setHouseNumber("22");
        normalUser.setPassword(getEncodedPassword("test123"));
        Set<Role> userRoles = new HashSet<>();
        userRoles.add(userRole);
        normalUser.setRole(userRoles);
        userDao.save(normalUser);
    }

    public String getEncodedPassword(String password){
        return passwordEncoder.encode(password);
    }
}
