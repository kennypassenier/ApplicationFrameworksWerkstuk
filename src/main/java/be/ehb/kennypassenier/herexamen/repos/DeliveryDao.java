package be.ehb.kennypassenier.herexamen.repos;

import be.ehb.kennypassenier.herexamen.entities.Delivery;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeliveryDao extends CrudRepository<Delivery, Integer> {

    Iterable<Delivery> findByOwnerIgnoreCase(String owner);
}
