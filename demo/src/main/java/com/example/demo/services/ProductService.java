package com.example.demo.services;
import com.example.demo.model.Product;
import com.example.demo.repository.ProductRepository;

import java.util.List;

public interface ProductService {
    List<Product> getAllProducts();

   void deleteProduct(Long id);
}

