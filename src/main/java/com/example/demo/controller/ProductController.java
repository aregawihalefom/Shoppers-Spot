package com.example.demo.controller;

import com.example.demo.domain.Product;
import com.example.demo.domain.ProductReview;
import com.example.demo.dto.ProductDto;
import com.example.demo.service.product.ProductService;
import com.example.demo.service.product.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "*")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    ReviewService reviewService;

    @GetMapping
    public List<ProductDto> findAll(){
        return productService.getAll();
    }


    @PostMapping
    public Product save(@RequestBody Product product){
        product.setCreatedAt(LocalDate.now());
        return productService.save(product);
    }

    @PutMapping("/{id}")
    public Product update(@RequestBody Product product , @PathVariable("id")Long id){
        return productService.save(product);
    }

    @GetMapping("/{id}")
    public ProductDto findById(@PathVariable("id") Long id){
        return productService.findById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id")Long id){
        productService.deleteById(id);
    }

    @GetMapping("/sellers/{username}")
    public List<Product> findByUserId(@PathVariable("username")String username){
        return productService.findProductByUsername(username);
    }

    @GetMapping("/{id}/reviews")
    public List<ProductReview> findProductReviewByProductId(@PathVariable("id")Long id){
        return reviewService.findByProductId(id);
    }

    @PostMapping("/{id}/reviews")
    public ProductReview addProductReview(@PathVariable("id")Long id,
                                                            @RequestBody ProductReview review
    ){

        Product product = productService.findByIdFull(id);
        product.addReview(review);

        for(ProductReview review1 : product.getProductReview())
            review1.setProduct(product);
        productService.save(product);

        return review;
    }

}
