package com.ataberk.viewconsumer.listener;

import com.ataberk.viewconsumer.model.View;
import com.ataberk.viewconsumer.repository.ViewRepository;
import com.ataberk.viewconsumer.service.ViewService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.commons.text.StringEscapeUtils;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class ConsumerService {

    @Autowired
    private ViewService viewService;

    Logger logger = LoggerFactory.getLogger(ConsumerService.class);

    @KafkaListener(topics = "views", groupId = "group-id")
    void consumer(String viewEventMessage) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
        viewEventMessage = viewEventMessage.replaceAll("^\"|\"$", "");
        View viewEvent = mapper.readValue(StringEscapeUtils.unescapeJson(viewEventMessage), View.class);

        viewService.save(viewEvent);
        logger.info("Event saved in MongoDB: " + viewEvent);
    }

}
