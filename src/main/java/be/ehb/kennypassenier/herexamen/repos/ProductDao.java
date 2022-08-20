package be.ehb.kennypassenier.herexamen.repos;

import be.ehb.kennypassenier.herexamen.entities.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductDao extends CrudRepository<Product, Integer> {
    // Extra functie toevoegen om op categorie te zoeken
    // Kan dankzij CRUD repo makkelijk worden uitgebreid als we de juiste syntax volgen
    // find -> select statement
    // by -> wordt opgevolgd door parameternaam
    // category -> naam waarom we zoeken
    // IgnoreCase -> spreekt voor zich, niet langer hoofdlettergevoelig
    Iterable<Product> findByCategoryIgnoreCase(String category);
}
