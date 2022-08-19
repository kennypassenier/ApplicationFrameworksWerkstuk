package be.ehb.kennypassenier.herexamen.entities;

public class JwtResponse {
    private User user;
    private String jwtToken;

    // Standaard constructor
    public JwtResponse(User user, String jwtToken) {
        this.user = user;
        this.jwtToken = jwtToken;
    }
    // Getters en setters
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getJwtToken() {
        return jwtToken;
    }

    public void setJwtToken(String jwtToken) {
        this.jwtToken = jwtToken;
    }
}
