package com.ataberk.recommendationapi.controller;

import com.ataberk.recommendationapi.model.Product;
import com.ataberk.recommendationapi.model.Recommendation;
import com.ataberk.recommendationapi.model.ViewHistory;
import com.ataberk.recommendationapi.service.ProductService;
import com.ataberk.recommendationapi.service.ViewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.Set;

@RestController
public class RecommendationController {

    @Autowired
    private ViewService viewService;

    @Autowired
    private ProductService productService;

    @GetMapping("/recommendation")
    public ResponseEntity<Recommendation> getRecommendationForUser(@RequestParam String userId) {
        ViewHistory userViewHistory = viewService.getViewHistoryOfUser(userId);
        Set<String> viewedCategories = new HashSet<>();
        Set<Product> recommendedProducts = new HashSet<>();

        if (userViewHistory.getProducts().isEmpty()) {
            recommendedProducts = productService.getTopBoughtProducts();

            return ResponseEntity.ok(new Recommendation(userId, recommendedProducts, "non-personalized"));
        }

        for (Product product : userViewHistory.getProducts()) {
            viewedCategories.add(product.getCategoryId());

            if (viewedCategories.size() == 3)
                break;
        }

        recommendedProducts = productService.getTopBoughtProductsByCategories(viewedCategories);

        return ResponseEntity.ok(new Recommendation(userId, recommendedProducts, "personalized"));
    }

}
