package com.example.demo.controller;

import com.example.demo.domain.ProductReview;
import com.example.demo.service.product.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@RestController("/product/reviews")

@CrossOrigin(origins = "*")
public class ReviewController {

    private final static Logger LOGGER =
            Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    @Autowired
    ReviewService reviewService;

    @GetMapping
    public List<ProductReview> getAll() {
        return reviewService.findAll();
    }

    @GetMapping("/{id}")
    public ProductReview getById(@PathVariable long id) {
        return reviewService.findById(id);
    }

    @PostMapping
    public void save(@RequestBody ProductReview user) {
        reviewService.save(user);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") long id) {
        reviewService.deleteById(id);
    }

}
