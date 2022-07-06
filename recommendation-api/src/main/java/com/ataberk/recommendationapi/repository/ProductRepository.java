package com.ataberk.recommendationapi.repository;

import com.ataberk.recommendationapi.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;

public interface ProductRepository extends JpaRepository<Product, String> {

    @Query(value = "SELECT product_user_bought.product_id, category_id FROM\n" +
            "    (SELECT product_id, category_id FROM products\n" +
            "    WHERE category_id IN :ids) AS category_product\n" +
            "    LEFT JOIN (SELECT product_id, user_id FROM orders JOIN order_items oi on orders.order_id = oi.order_id GROUP BY user_id, product_id) AS product_user_bought\n" +
            "    ON product_user_bought.product_id=category_product.product_id\n" +
            "GROUP BY product_user_bought.product_id, category_id\n" +
            "ORDER BY count(user_id) DESC LIMIT 10;", nativeQuery = true)
    Set<Product> getTopBoughtProductsByCategoryId(@Param("ids") Set<String> categories);

    @Query(value = "SELECT product_user_bought.product_id, category_id FROM products\n" +
            "    LEFT JOIN (SELECT product_id, user_id FROM orders JOIN order_items oi on orders.order_id = oi.order_id\n" +
            "    GROUP BY user_id, product_id) AS product_user_bought\n" +
            "    ON product_user_bought.product_id=products.product_id\n" +
            "GROUP BY product_user_bought.product_id, category_id\n" +
            "ORDER BY count(user_id) DESC LIMIT 10;", nativeQuery = true)
    Set<Product> getTopBoughtProducts();

    @Query(value = "SELECT category_id FROM products WHERE product_id = ?1", nativeQuery = true)
    String getCategoryIdByProductId(String productId);
}
