package com.ataberk.recommendationapi.repository;

import com.ataberk.recommendationapi.model.View;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface ViewRepository extends MongoRepository<View, String> {

    @Query(value = "{userId:?0}", sort= "{timestamp:-1}")
    List<View> getByUserIdSortByTimestamp(String userId, Pageable page);

    @Query(value = "{'userId' : ?0, 'properties.productId' : ?1}", delete = true)
    void deleteByProductIdAndUserId(String userId, String productId);
}
