package pt.iade.mypastry.webserver.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import pt.iade.mypastry.webserver.models.Order;
import pt.iade.mypastry.webserver.models.OrderProduct;
import pt.iade.mypastry.webserver.models.repositories.OrderProductRepository;
import pt.iade.mypastry.webserver.models.repositories.OrderRepository;
import pt.iade.mypastry.webserver.results.Response;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/ordprods")
public class OrdProdController {
    private final Logger logger = LoggerFactory.getLogger(OrdProdController.class);

    @Autowired
    private OrderProductRepository orderProductRepository;

    @Autowired
    private OrderRepository orderRepository;

    @GetMapping(path = "/{orderId:[0-9]+}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<OrderProduct> getAllByOrder(@PathVariable int orderId){
        logger.info("OrdProd-> Sending all products from order with id="+orderId+".");

        Optional<Order> order_ = orderRepository.findById(orderId);

        assert order_.isPresent();
        return order_.get().getOrdProds();
    }

    @PostMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public OrderProduct addProdToOrder(@RequestBody OrderProduct orderProduct){
        logger.info("OrdProd-> Adding a new product to the order with id="+orderProduct.getOrder().getId()+".");

        return orderProductRepository.save(orderProduct);
    }

    @PostMapping(path = "/{id:[0-9]+}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Response updateOrdProd(@PathVariable int id, @RequestBody OrderProduct updatedProduct){
        logger.info("OrdProd-> Updating OrderProduct with id="+id+".");

        Optional<OrderProduct> ordProd_ = orderProductRepository.findById(id);
        if (ordProd_.isPresent()){
            ordProd_.get().setQuantity(updatedProduct.getQuantity());
            ordProd_.get().setSubTotal(updatedProduct.getSubTotal());

            orderProductRepository.save(ordProd_.get());
            return new Response("Product with id="+id+
                    " was successfully updated in order with id="+ordProd_.get().getOrder().getId()+".", null);
        }

        return new Response("Product with id="+id+
                " was not found.", null);
    }

    @DeleteMapping(path = "/{id:[0-9]+}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Response deleteOrdProd(@PathVariable int id){
        logger.info("OrdProd-> Removing the OrdProd with id="+id+".");
        orderProductRepository.deleteById(id);

        return new Response("Product with id="+id+
                " was successfully removed.", null);
    }

}
