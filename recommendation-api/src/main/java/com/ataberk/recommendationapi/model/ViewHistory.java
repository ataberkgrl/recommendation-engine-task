package com.ataberk.recommendationapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ViewHistory {

    private String userId;
    private List<Product> products;
    private String type;

}
