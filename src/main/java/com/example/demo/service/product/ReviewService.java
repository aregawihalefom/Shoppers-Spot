package com.example.demo.service.product;
import com.example.demo.domain.Product;
import com.example.demo.domain.ProductReview;
import com.example.demo.domain.User;
import org.springframework.stereotype.Service;

import java.util.List;


public interface ReviewService {

    public List<ProductReview> findAll();

    public ProductReview findById(long id);

    public ProductReview save(ProductReview review);

    public void deleteById(long id);

    public ProductReview approveReview(Long id, boolean b);

    public List<ProductReview> findByUserId(Long id);

    public List<ProductReview> findByProductId(Long id);
}
