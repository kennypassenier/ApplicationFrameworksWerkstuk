package be.ehb.kennypassenier.herexamen.repos;

import be.ehb.kennypassenier.herexamen.entities.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleDao extends CrudRepository<Role, String> {

}
