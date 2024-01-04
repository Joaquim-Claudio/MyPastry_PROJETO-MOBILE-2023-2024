package pt.iade.mypastry.webserver.controllers;

import org.aspectj.weaver.ast.Or;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import pt.iade.mypastry.webserver.enums.OrderStatus;
import pt.iade.mypastry.webserver.models.Order;
import pt.iade.mypastry.webserver.models.OrderProduct;
import pt.iade.mypastry.webserver.models.User;
import pt.iade.mypastry.webserver.models.repositories.OrderProductRepository;
import pt.iade.mypastry.webserver.models.repositories.OrderRepository;
import pt.iade.mypastry.webserver.models.repositories.UserRepository;
import pt.iade.mypastry.webserver.results.Response;

import java.nio.file.Path;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/orders")
public class OrderController {
    private final Logger logger = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderProductRepository productRepository;

    @GetMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Order> getAll() {
        logger.info("Sending all the orders.");
        return orderRepository.findAll();
    }

    @PostMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public Order addOrder(@RequestBody Order order){
        logger.info("Order-> Adding a new order to user with id="+order.getUserId()+".");

        return orderRepository.save(order);
    }

    @PostMapping(path = "/{orderId:[0-9]+}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Response updateOrder(@PathVariable int orderId, @RequestBody Order updatedOrder) {
        logger.info("Order-> Updating order with id="+orderId);

        Optional<Order> order = orderRepository.findById(orderId);
        if (order.isPresent()){
            order.get().setStatus(updatedOrder.getStatus());
            order.get().setTotal(updatedOrder.getTotal());

            orderRepository.save(order.get());
        }

        return new Response("Order with id="+orderId+
                " was successfully updated.", null);
    }

    @DeleteMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public Response deleteOrder(@RequestParam int id){
        logger.info("Order-> Removing order with id="+id+".");
        orderRepository.deleteById(id);

        return new Response("Order with id="+id+" was successfully removed!", null);
    }

    @GetMapping(path = "/user", produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Order> getAllByUserId(@RequestParam int userId){
        logger.info("Order/User-> Sending all orders of user with id="+userId+".");

        return orderRepository.findAllByUserId(userId);
    }

    @GetMapping(path = "/user/pending", produces = MediaType.APPLICATION_JSON_VALUE)
    public Order getPendingOrder(@RequestParam int userId){
        logger.info("Order/User-> Sending the Pending Order of user with id="+userId+".");

        return orderRepository.findByUserIdAndStatus(userId, OrderStatus.PENDING);
    }

    @GetMapping(path = "/{orderId:[0-9]+}/products", produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<OrderProduct> getAllProducts(@PathVariable int orderId){
        logger.info("Order/Product-> Sending all products from order with id="+orderId+".");

        return productRepository.findAllByOrderId(orderId);
    }

    @PostMapping(path = "/{orderId:[0-9]+}/products", produces = MediaType.APPLICATION_JSON_VALUE)
    public OrderProduct addProdToOrder(@PathVariable int orderId, @RequestBody OrderProduct orderProduct){
        logger.info("Order/Product-> Adding a new product to the order with id="+orderId+".");

        return productRepository.save(orderProduct);
    }

    @PostMapping(path = "/{orderId:[0-9]+}/products/{prodId:[0-9]+}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Response updateProdOfOrder(@PathVariable int orderId, @PathVariable int prodId, @RequestBody OrderProduct updatedProduct){
        logger.info("Order-> Updating product with id="+prodId+" in order with id="+orderId+".");

        Optional<OrderProduct> product = productRepository.findById(prodId);
        if (product.isPresent()){
            product.get().setQuantity(updatedProduct.getQuantity());
            product.get().setSubTotal(updatedProduct.getSubTotal());

            productRepository.save(product.get());
        }

        return new Response("Product with id="+prodId+
                " was successfully updated in order with id="+orderId+".", null);
    }

    @DeleteMapping(path = "/{orderId:[0-9]+}/products/{prodId:[0-9]+}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Response deleteProdFromOrder(@PathVariable int orderId, @PathVariable int prodId){
        logger.info("Order-> Removing the order with id="+prodId+".");
        productRepository.deleteById(prodId);

        return new Response("Product with id="+prodId+
                " was successfully removed from order with id="+orderId+".", null);
    }
}






















