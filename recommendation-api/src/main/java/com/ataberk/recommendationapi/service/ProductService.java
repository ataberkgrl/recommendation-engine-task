package com.ataberk.recommendationapi.service;

import com.ataberk.recommendationapi.model.Product;
import com.ataberk.recommendationapi.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Set<Product> getTopBoughtProductsByCategories(Set<String> categories) {
        return productRepository.getTopBoughtProductsByCategoryId(categories);
    }

    public Set<Product> getTopBoughtProducts() {
        return productRepository.getTopBoughtProducts();
    }
}
