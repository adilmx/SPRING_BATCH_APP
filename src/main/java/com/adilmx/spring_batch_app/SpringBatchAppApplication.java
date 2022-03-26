package com.adilmx.spring_batch_app;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource("classpath:/configuration-batch.xml")
@EnableBatchProcessing
public class SpringBatchAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBatchAppApplication.class, args);
    }

}
