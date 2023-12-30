package pt.iade.mypastry.webserver.models.repositories;

import org.springframework.data.repository.CrudRepository;
import pt.iade.mypastry.webserver.models.User;

public interface UserRepository extends CrudRepository<User, Integer> {
    public User findByEmailAndPassword(String email, String password);
}
