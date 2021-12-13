package com.example.demo.controller;

import com.example.demo.domain.ERole;
import com.example.demo.domain.Product;
import com.example.demo.domain.Role;
import com.example.demo.domain.User;
import com.example.demo.dto.UserDto;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*")
public class UserController {

    private final static Logger LOGGER =
            Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    @Autowired
    UserService userService;

    @GetMapping
    public List<UserDto> getAll(){
        return userService.getAll();
    }

    @GetMapping("/{id}")
    public User getById(@PathVariable long id){ return userService.findById(id); }

    @PostMapping
    public void save(@RequestBody User user){
        userService.save(user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable("id") long id){
        userService.deleteById(id);
    }

    @PostMapping("/sellers/{id}")
    public Product addProduct(@PathVariable("id")Long id , @RequestBody Product product){
        User user =  userService.findById(id);

        LOGGER.warning("User name adding  " + user.getUsername());

        if(user !=null)
        {
            product.setCreatedAt(LocalDate.now());
            user.addProduct(product);
            for(Product product1 : user.getProducts())
            {
                product1.setUser(user);
            }
            userService.save(user);
        }
        return  product;
    }

    @GetMapping("/sellers")
    public List<User> findSellers(){

        List<User> users = userService.findAll();

        Role sellerRole = new Role(ERole.SELLER.name());
        List<User> sellers =   users.stream()
                .filter(user->user.getRoles().contains(sellerRole)).collect(Collectors.toList());
        return sellers;
    }

}
