package be.ehb.kennypassenier.herexamen.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
public class Delivery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull(message = "Naam van eigenaar moet ingevuld zijn")
    @Size(min = 2, max = 40, message = "Naam moet tussen 2 en 40 karakters lang zijn")
    private String owner;

    @NotNull(message = "Prijs kan niet leeg zijn")
    private double price;

    @NotNull(message = "Naam van product mag niet leeg zijn")
    private String productName;


    public Delivery(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }
}
