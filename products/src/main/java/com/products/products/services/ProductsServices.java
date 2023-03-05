package com.products.products.services;

import com.products.products.dtos.ProductDtos;
import com.products.products.models.Product;
import com.products.products.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductsServices {
    @Autowired
    ProductRepository productRepository;

    public List findAll() {
        return productRepository.findAll();
    }

    public Optional<Product> findById(int id){
        return productRepository.findById(id);
    }

    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    public void delete(int id){
        productRepository.deleteById(id);
    }
}
