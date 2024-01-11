package pt.iade.mypastry.webserver.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import pt.iade.mypastry.webserver.enums.OrderStatus;
import pt.iade.mypastry.webserver.enums.OrderType;
import pt.iade.mypastry.webserver.models.Order;
import pt.iade.mypastry.webserver.models.OrderProduct;
import pt.iade.mypastry.webserver.models.User;
import pt.iade.mypastry.webserver.models.repositories.OrderProductRepository;
import pt.iade.mypastry.webserver.models.repositories.OrderRepository;
import pt.iade.mypastry.webserver.models.repositories.UserRepository;
import pt.iade.mypastry.webserver.results.Response;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/orders")
public class OrderController {
    private final Logger logger = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private UserRepository userRepository;

    @GetMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Order> getAll() {
        logger.info("Sending all the orders.");
        return orderRepository.findAll();
    }

    @PostMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public Order addOrder(@RequestBody Order order){
        logger.info("Order-> Adding a new order to user with id="+order.getUser().getId()+".");
        return orderRepository.save(order);
    }

    @PostMapping(path = "/{id:[0-9]+}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Order updateOrder(@PathVariable int id, @RequestBody Order updatedOrder) {
        logger.info("Order-> Updating order with id="+id);

        Optional<Order> order = orderRepository.findById(id);

        if (order.isPresent()){
            order.get().setStatus(updatedOrder.getStatus());
            order.get().setTotal(updatedOrder.getTotal());
            order.get().setDeliveryCost(updatedOrder.getDeliveryCost());
            order.get().setDeliveryAddress(updatedOrder.getDeliveryAddress());

            Order savedOrder = orderRepository.save(order.get());
            logger.info("Status:"+savedOrder.getStatus().toString());
            return savedOrder;
        }

        return null;
    }

    @DeleteMapping(path = "/{id:[0-9]+}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Response deleteOrder(@RequestParam int id){
        logger.info("Order-> Removing order with id="+id+".");
        orderRepository.deleteById(id);

        return new Response("Order with id="+id+" was successfully removed!", null);
    }



    @GetMapping(path = "/user/{userId:[0-9]+}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Order> getAllByUser(@PathVariable int userId){
        logger.info("Order/User-> Sending all orders of user with id="+userId+".");
        Optional<User> user_ = userRepository.findById(userId);

        assert user_.isPresent();
        return user_.get().getOrders();
    }

    @GetMapping(path = "/user/{userId:[0-9]+}/pending", produces = MediaType.APPLICATION_JSON_VALUE)
    public Order getPendingOrder(@PathVariable int userId){
        logger.info("Order/User-> Sending the Pending Order of user with id="+userId+".");
        Optional<User> user_ = userRepository.findById(userId);

        assert user_.isPresent();
        for (Order o : user_.get().getOrders()){
            if (o.getStatus() == OrderStatus.PENDING){
                return o;
            }
        }

        return null;
    }

}






















