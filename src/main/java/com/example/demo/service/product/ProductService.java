package com.example.demo.service.product;



import com.example.demo.domain.Product;
import com.example.demo.domain.User;
import com.example.demo.dto.ProductDto;

import java.util.List;

public interface ProductService {


    public List<ProductDto> getAll();

    public ProductDto findById(long id);

    public Product save(Product product);

    public List<Product> getProductByName(String name);

    public List<Product> findProductByUsername(String username);

    public void deleteById(long id);

    public Product findByIdFull(Long id);

}
