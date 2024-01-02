package pt.iade.mypastry.webserver.models.repositories;

import org.springframework.data.repository.CrudRepository;
import pt.iade.mypastry.webserver.models.Address;

public interface AddressRepository extends CrudRepository<Address, Integer> {
}
