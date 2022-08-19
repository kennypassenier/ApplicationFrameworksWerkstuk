package be.ehb.kennypassenier.herexamen.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.validation.constraints.*;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull(message = "Naam moet ingevuld zijn")
    @Size(min = 2, max = 40, message = "Naam moet tussen 2 en 40 karakters lang zijn")
    private String name;

    @NotNull(message = "Prijs kan niet leeg zijn")
    private double price;

    @NotNull(message = "Omschrijving moet ingevuld zijn")
    @Size(min = 2, max = 100, message = "Omschrijving moet tussen 2 en 100 karakters lang zijn")
    private String description;

    @NotNull(message = "Categorie moet ingevuld zijn")
    @Size(min = 2, max = 50, message = "Categorie moet tussen 2 en 50 karakters lang zijn")
    private String category;

    // Standaard constructor
    public Product(){

    }

    // Getters en setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
