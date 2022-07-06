package com.ataberk.viewproducer;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaTemplate;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
public class ViewProducerApplication {

	Logger logger = LoggerFactory.getLogger(ViewProducerApplication.class);

	@Value("${page.view.source}")
	String pageViewsDataSourcePath;

	@Value("${kafka.topic}")
	String kafkaTopic;

	public static void main(String[] args) {
		SpringApplication.run(ViewProducerApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(KafkaTemplate<String, String> kafkaTemplate) {
		return args -> {
			BufferedReader reader;

			try {
				reader = new BufferedReader(new FileReader(pageViewsDataSourcePath));
				String line = reader.readLine();

				while (line != null) {
					// add timestamp to event data
					ObjectMapper mapper = new ObjectMapper();
					JsonNode lineJson = mapper.readTree(line);
					Timestamp timestamp = new Timestamp(System.currentTimeMillis());
					((ObjectNode) lineJson).put("timestamp", String.valueOf(timestamp));

					kafkaTemplate.send(kafkaTopic, String.valueOf(lineJson));
					logger.info("Event sent to Kafka: " + String.valueOf(lineJson));

					line = reader.readLine();
					TimeUnit.SECONDS.sleep(1);
				}

				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		};
	}
}
