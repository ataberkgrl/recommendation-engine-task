package com.ataberk.recommendationapi.controller;

import com.ataberk.recommendationapi.model.ViewHistory;
import com.ataberk.recommendationapi.service.ViewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ViewHistoryController {

    @Autowired
    private ViewService viewService;

    @GetMapping("/view-history")
    public ResponseEntity<ViewHistory> getViewHistory(@RequestParam String userId) {
        return ResponseEntity.ok(viewService.getViewHistoryOfUser(userId));
    }

    @DeleteMapping("/view-history")
    public ResponseEntity<String> deleteProductFromUserViewHistory(@RequestParam String userId, @RequestParam String productId) {
        viewService.deleteProductFromUserViewHistory(userId, productId);
        return ResponseEntity.ok("Product deleted from user view history.");
    }
}
