package com.ataberk.recommendationapi.service;

import com.ataberk.recommendationapi.model.Product;
import com.ataberk.recommendationapi.model.View;
import com.ataberk.recommendationapi.model.ViewHistory;
import com.ataberk.recommendationapi.repository.ProductRepository;
import com.ataberk.recommendationapi.repository.ViewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ViewService {

    @Autowired
    private ViewRepository viewRepository;

    @Autowired
    private ProductRepository productRepository;

    public ViewHistory getViewHistoryOfUser(String userId) {
        List<View> lastViews = viewRepository.getByUserIdSortByTimestamp(userId, PageRequest.of(0, 10));
        List<Product> viewedProducts = new ArrayList<>();

        for (View view : lastViews) {
            String categoryId = productRepository.getCategoryIdByProductId(view.getProperties().getProductId());
            Product product = new Product(view.getProperties().getProductId(), categoryId);
            viewedProducts.add(product);
        }

        return new ViewHistory(userId, viewedProducts, "personalized");
    }

    public void deleteProductFromUserViewHistory(String userId, String productId) {
        viewRepository.deleteByProductIdAndUserId(userId, productId);
    }
}
