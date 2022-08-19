package be.ehb.kennypassenier.herexamen.repos;

import be.ehb.kennypassenier.herexamen.entities.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends CrudRepository<User, String> {

}
