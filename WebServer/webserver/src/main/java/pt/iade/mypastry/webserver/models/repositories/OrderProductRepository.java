package pt.iade.mypastry.webserver.models.repositories;

import org.springframework.data.repository.CrudRepository;
import pt.iade.mypastry.webserver.models.OrderProduct;

public interface OrderProductRepository extends CrudRepository<OrderProduct, Integer> {
    public Iterable<OrderProduct> findAllByOrderId(int orderId);
}
