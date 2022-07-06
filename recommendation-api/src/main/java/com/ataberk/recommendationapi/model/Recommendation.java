package com.ataberk.recommendationapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Recommendation {

    private String userId;
    private Set<Product> products;
    private String type;
}
