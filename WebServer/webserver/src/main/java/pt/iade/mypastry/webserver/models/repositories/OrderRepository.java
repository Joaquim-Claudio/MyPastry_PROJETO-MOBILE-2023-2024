package pt.iade.mypastry.webserver.models.repositories;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import pt.iade.mypastry.webserver.enums.OrderStatus;
import pt.iade.mypastry.webserver.models.Order;
import pt.iade.mypastry.webserver.models.OrderProduct;
import pt.iade.mypastry.webserver.models.User;

import java.util.ArrayList;

public interface OrderRepository extends CrudRepository<Order, Integer> {
    public Iterable<Order> findAllByUser(User user);
    public ArrayList<Order> findAllByStatus(OrderStatus status);
    public Order findByUserAndStatus(User user, OrderStatus status);
}
