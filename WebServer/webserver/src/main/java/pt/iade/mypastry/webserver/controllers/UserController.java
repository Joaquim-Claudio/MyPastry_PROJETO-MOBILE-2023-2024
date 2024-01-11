package pt.iade.mypastry.webserver.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import pt.iade.mypastry.webserver.models.Address;
import pt.iade.mypastry.webserver.models.Order;
import pt.iade.mypastry.webserver.models.User;
import pt.iade.mypastry.webserver.models.repositories.AddressRepository;
import pt.iade.mypastry.webserver.models.repositories.UserRepository;
import pt.iade.mypastry.webserver.results.Response;

import java.nio.file.Path;
import java.util.Optional;
import java.util.Random;

@RestController
@RequestMapping(path = "/api/users")
public class UserController {
    private final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AddressRepository addressRepository;


    @GetMapping(path = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<User> getUsers() {
        logger.info("User-> Sending all the users.");
        return userRepository.findAll();
    }


    @GetMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public User getUserByEmail(@RequestParam String email, @RequestParam String password) {
        User user = userRepository.findByEmailAndPassword(email, password);
        if(user == null) {
            //  TODO: implement NotFoundException
            logger.info("User-> User email="+email+ " was not found.");
            return null;
        } else {
            logger.info("User-> Sending the user with id="+user.getId()+".");
            return user;
        }
    }


    @PostMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public User addUser(@RequestBody User user) {
        logger.info("User-> Adding a new user.");
        user.setAddress(addressRepository.save(user.getAddress()));
        return userRepository.save(user);

    }

    @PostMapping(path = "/{id:[0-9]+}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Response updateUser(@PathVariable int id, @RequestBody User updatedUser) {
        logger.info("User-> Updating user with id="+id);

        Optional<Address> userAddress_ = addressRepository.findById(updatedUser.getAddress().getId());
        if (userAddress_.isPresent()){
            userAddress_.get().setStreet(updatedUser.getAddress().getStreet());
            userAddress_.get().setPostalCode(updatedUser.getAddress().getPostalCode());
            userAddress_.get().setBuilding(updatedUser.getAddress().getBuilding());
            userAddress_.get().setDoor(updatedUser.getAddress().getDoor());
            userAddress_.get().setCity(updatedUser.getAddress().getCity());

            addressRepository.save(userAddress_.get());
        }

        Optional<User> user_ = userRepository.findById(id);
        if (user_.isPresent()){
            user_.get().setEmail(updatedUser.getEmail());
            user_.get().setPassword(updatedUser.getPassword());
            user_.get().setPoints(updatedUser.getPoints());
            user_.get().setAddress(updatedUser.getAddress());
            user_.get().setOrders(updatedUser.getOrders());

            userRepository.save(user_.get());
        }

        return new Response("User with id="+id+
                " was successfully updated.", null);
    }


    @DeleteMapping(path = "/{id:[0-9]+}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Response removeUser(@PathVariable(name = "id") int id){
        logger.info("User-> Removing the user with id="+id+".");
        userRepository.deleteById(id);
        return new Response("User with id="+id+" was successfully removed!", null);
    }

}
