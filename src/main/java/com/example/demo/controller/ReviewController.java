package com.example.demo.controller;

import com.example.demo.domain.Product;
import com.example.demo.domain.ProductReview;
import com.example.demo.domain.User;
import com.example.demo.service.product.ProductService;
import com.example.demo.service.product.ReviewService;
import com.example.demo.service.user.UserService;
import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api/reviews")
@CrossOrigin(origins = "*")
public class ReviewController {

    private final static Logger LOGGER =
            Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    @Autowired
    ReviewService reviewService;

    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService;

    @GetMapping
    public List<ProductReview> getAll() {
        return reviewService.findAll();
    }

    @GetMapping("/{id}")
    public ProductReview getById(@PathVariable long id) {
        return reviewService.findById(id);
    }

    @PostMapping
    public ProductReview save(@RequestBody ProductReview review){
        return reviewService.save(review);
    }
    @PutMapping("/{id}/{userId}")
    public void save( @RequestBody ProductReview review, @PathVariable("id") Long id, @PathVariable("userId")Long userId) {

        Product product = productService.findByIdFull(id);
        review.setStatus(false);
        review.setReviewedAt(LocalDate.now());
        product.getProductReview().add(review);
        productService.save(product);
        User user = userService.findById(userId);
        user.getReviews().add(review);
        userService.save(user);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") long id) {
        reviewService.deleteById(id);
    }

}
