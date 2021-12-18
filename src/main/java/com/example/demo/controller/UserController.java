package com.example.demo.controller;

import com.example.demo.domain.*;
import com.example.demo.dto.UserDto;
import com.example.demo.service.product.ReviewService;
import com.example.demo.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*")
public class UserController {

    private final static Logger LOGGER =
            Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    @Autowired
    UserService userService;

    @Autowired
    ReviewService reviewService;

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

        Role sellerRole = new Role(ERole.ROLE_SELLER.name());
        List<User> sellers =   users.stream()
                .filter(user->user.getRoles().contains(sellerRole)).collect(Collectors.toList());
        return sellers;
    }

    @PutMapping("/sellers/{id}")
    public User approveSeller(@PathVariable("id") Long id){
        return userService.approveSeller(id,true);
    }

    @PutMapping("/sellers/dis/{id}")
    public User diApproveSeller(@PathVariable("id") Long id){
        return userService.approveSeller(id,false);
    }


    @GetMapping("/{id}/reviews")
    public List<ProductReview> findByUserId(@PathVariable("id")Long id) {
        return  userService.findById(id).getReviews();
    }

}
