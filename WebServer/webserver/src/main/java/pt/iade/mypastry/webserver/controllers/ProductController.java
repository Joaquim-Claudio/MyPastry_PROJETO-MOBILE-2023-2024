package pt.iade.mypastry.webserver.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import pt.iade.mypastry.webserver.enums.ProductType;
import pt.iade.mypastry.webserver.models.Product;
import pt.iade.mypastry.webserver.models.repositories.ProductRepository;
import pt.iade.mypastry.webserver.results.Response;

import java.util.Optional;

@RestController
@RequestMapping(path = "/api/products")
public class ProductController {
    private final Logger logger = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private ProductRepository productRepository;

    @GetMapping(path = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Product> getProducts(){
        logger.info("Product-> Sending all the products.");
        return productRepository.findAll();
    }

    @GetMapping(path = "{id:[0-9]+}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Product getProductById(@PathVariable(name = "id") int id){
        Optional<Product> product = productRepository.findById(id);

        if (product.isEmpty()){
            logger.info("Product-> Product with id="+id+" was not found");
            return null;

        } else {
            logger.info("Product-> Sending product with id="+id);
            return product.get();
        }
    }

    @GetMapping(path = "/delicacies", produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Product> getDelicacies(){
        logger.info("Product-> Sending all the Delicacies.");
        return productRepository.findByDelicacyTrue();
    }

    @GetMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Product> getProductByType(@RequestParam(name = "type")ProductType type){
        logger.info("Product-> Sending all the products with type="+type.toString());
        return productRepository.findAllByType(type);
    }


    @PostMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public Product addProduct(@RequestBody Product product){
        logger.info("Product-> Adding a new product.");
        return productRepository.save(product);
    }

    @DeleteMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public Response deleteProduct(@RequestParam(name = "id") int id){
        logger.info("Product-> Deleting product with id="+id);
        productRepository.deleteById(id);
        return new Response("Product-> Product with id="+id+" was successfully deleted!", null);
    }
}