package be.ehb.kennypassenier.herexamen.entities;

public class JwtRequest {

    private String userName;
    private String userPassword;

    // Getters en setters
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }
}
