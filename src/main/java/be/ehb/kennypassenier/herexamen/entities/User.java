package be.ehb.kennypassenier.herexamen.entities;

import javax.persistence.*;
import java.util.Set;

@Entity
public class User {

    @Id
    private String userName;
    private String firstName;
    private String lastName;
    private String password;
    private String street;
    private String city;
    private String houseNumber;
    private String postalCode;

    // User kan verschillende rollen hebben
    // We willen dat de relatie eager geladen wordt
    // Als een user verwijderd wordt, dan verwijderen we ook de relaties
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    // Maakt de pivot table aan tussen USER en ROLE
    @JoinTable(name = "USER_ROLE",
        joinColumns = {
            @JoinColumn(name = "USER_ID")
        },
        inverseJoinColumns = {
            @JoinColumn(name = "ROLE_ID")
        }
    )
    private Set<Role> role;

    // Getters en setters
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String userPassword) {
        this.password = userPassword;
    }

    public Set<Role> getRole() {
        return role;
    }

    public void setRole(Set<Role> role) {
        this.role = role;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String streetNumber) {
        this.houseNumber = streetNumber;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }
}
