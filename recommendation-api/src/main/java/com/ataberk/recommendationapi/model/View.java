package com.ataberk.recommendationapi.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document
@Data
@NoArgsConstructor
public class View {
    @Id
    private String id;

    @JsonProperty("event")
    private String event;

    @JsonProperty("messageid")
    private String messageId;

    @JsonProperty("userid")
    private String userId;

    @JsonProperty("properties")
    Properties properties;

    @JsonProperty("context")
    Context context;

    @JsonProperty("timestamp")
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    private Date timestamp;

    public View(String event, String messageid, String userid, Properties properties, Context context, Date timestamp) {
        this.event = event;
        this.messageId = messageid;
        this.userId = userid;
        this.properties = properties;
        this.context = context;
        this.timestamp = timestamp;
    }
}

