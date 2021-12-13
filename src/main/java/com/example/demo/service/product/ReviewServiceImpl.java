package com.example.demo.service.product;

import com.example.demo.domain.ProductReview;
import com.example.demo.domain.User;
import com.example.demo.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class ReviewServiceImpl implements ReviewService{

    @Autowired
    public ReviewRepository  reviewRepository;


    @Override
    public List<ProductReview> findAll() {
        List<ProductReview> reviews = new ArrayList<>();
        reviewRepository.findAll().forEach(reviews::add);
        return reviews;
    }

    @Override
    public ProductReview findById(long id) {
        return reviewRepository.findById(id).orElseThrow(null);
    }

    @Override
    public ProductReview save(ProductReview review) {
        return reviewRepository.save(review);
    }

    @Override
    public void deleteById(long id) {
        reviewRepository.deleteById(id);
    }

    @Override
    public ProductReview approveReview(Long id, boolean b) {
        ProductReview review = reviewRepository.findById(id).orElse(null);
        review.setStatus(b);
        return review;
    }

    @Override
    public List<ProductReview> findByUserId(Long id) {
        return reviewRepository.findProductReviewByUserId(id);
    }

    @Override
    public List<ProductReview> findByProductId(Long id) {
        return reviewRepository.findProductReviewByProductId(id);
    }
}
