package com.ataberk.recommendationapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Properties {

    @JsonProperty("productid")
    private String productId;
}
