package com.getir.readingisgood;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

@EnableMongoAuditing
@SpringBootApplication
public class ReadingIsGoodApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReadingIsGoodApplication.class, args);
    }

}
