package com.products.products.controller;

import com.products.products.dtos.ProductDtos;
import com.products.products.models.Product;
import com.products.products.services.ProductsServices;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Controller
@RequestMapping("/")
public class ProductController {
    @Autowired
    ProductsServices productsServices;


    public ResponseEntity<Object> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(productsServices.findAll());
    }

    @GetMapping
    public ResponseEntity<Object> get(){
        return ResponseEntity.status(HttpStatus.OK).body(productsServices.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getById(@PathVariable(value = "id")int id) {
        Optional<Product> productOptional = productsServices.findById(id);
        if (productOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(productsServices.findById(id));
        }
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Id not exits in bd");
    }

    @PostMapping
    public ResponseEntity<Object> addProduct(@RequestBody @Valid ProductDtos productDtos) {

        Product product = new Product();

        product.setName(productDtos.getName());
        product.setDescription(productDtos.getDescription());
        product.setPrice(productDtos.getPrice());
        Product saveProduct = productsServices.addProduct(product);

        ProductDtos productResponse = new ProductDtos();
        productResponse.setId(saveProduct.getId());
        productResponse.setName(saveProduct.getName());
        productResponse.setDescription(saveProduct.getDescription());
        productResponse.setPrice(saveProduct.getPrice());

        return ResponseEntity.status(HttpStatus.CREATED).body(productResponse);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete (@PathVariable(value = "id") int id) {
        productsServices.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body("Delet sucefull");
    }

}
