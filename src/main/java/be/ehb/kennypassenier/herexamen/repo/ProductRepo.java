package be.ehb.kennypassenier.herexamen.dao;

import be.ehb.kennypassenier.herexamen.models.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductDAO  extends CrudRepository<Product, Integer> {
    // Extra functie toevoegen om op categorie te zoeken
    // Kan dankzij CRUD repo makkelijk worden uitgebreid als we de juiste syntax volgen
    // find -> select statement
    // by -> wordt opgevolgd door parameternaam
    // category -> naam waarom we zoeken
    // IgnoreCase -> spreekt voor zich, niet langer hoofdlettergevoelig
    Iterable<Product> findByCategoryIgnoreCase(String category);
}
