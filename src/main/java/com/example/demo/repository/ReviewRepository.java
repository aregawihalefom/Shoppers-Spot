package com.example.demo.repository;

import com.example.demo.domain.ProductReview;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends CrudRepository<ProductReview, Long> {

    public List<ProductReview> findProductReviewByStatus(boolean b);
}
