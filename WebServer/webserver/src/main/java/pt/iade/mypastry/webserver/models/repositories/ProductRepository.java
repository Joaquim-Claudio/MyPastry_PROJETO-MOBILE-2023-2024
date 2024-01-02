package pt.iade.mypastry.webserver.models.repositories;

import org.springframework.data.repository.CrudRepository;
import pt.iade.mypastry.webserver.enums.ProductType;
import pt.iade.mypastry.webserver.models.Product;

public interface ProductRepository extends CrudRepository<Product, Integer> {
    public Iterable<Product> findAllByType(ProductType type);
    public Iterable<Product> findByDelicacyTrue();
}