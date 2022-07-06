package com.ataberk.viewconsumer.service;

import com.ataberk.viewconsumer.model.View;
import com.ataberk.viewconsumer.repository.ViewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ViewService {

    @Autowired
    private ViewRepository viewRepository;

    public void save(View viewEvent) {
        viewRepository.save(viewEvent);
    }
}
