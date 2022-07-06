package com.ataberk.viewconsumer.repository;

import com.ataberk.viewconsumer.model.View;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ViewRepository extends MongoRepository<View, String> {
}
