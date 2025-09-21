package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class ProdavnicaApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProdavnicaApplication.class, args);
    }

}
