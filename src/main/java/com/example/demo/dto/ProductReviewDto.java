package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductReviewDto {
    private Long id;
    private String title;
    private String content;
    private LocalDate reviewedAt;
    private Double rating;
    private boolean isApproved;
    private LocalDate approvedAt;
}
