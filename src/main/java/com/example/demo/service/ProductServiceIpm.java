package com.example.demo.service;

import com.example.demo.domain.Product;
import com.example.demo.dto.ProductDto;
import com.example.demo.helper.ListMapper;
import com.example.demo.repository.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.List;
@Service
public class ProductServiceIpm implements  ProductService{

    @Autowired
    private ProductRepository productRepository;


    @Autowired
    ModelMapper modelMapper;
    @Autowired

    ListMapper<Product, ProductDto> listMapperProductToDto;

    public List<ProductDto> getAll() {
        return (List<ProductDto>) listMapperProductToDto.mapList(productRepository.findAll(),new ProductDto());
    }

    @Override
    public ProductDto findById(long id) {
        return modelMapper.map(productRepository.findById(id).orElse(null), ProductDto.class);
    }

    @Override
    public Product save(Product product) {
        product.setCreatedAt(LocalDate.now());
     return    productRepository.save(modelMapper.map(product, Product.class));

    }

    @Override
    public List<Product> getProductByName(String name) {
        return productRepository.getProductByName(name);

    }
    @Override
    public List<Product> findProductByUsername(String username) {
        return productRepository.findProductByUserUsername(username);
    }

    @Override
    public void deleteById(long id) {
       productRepository.deleteById(id);
    }
}
