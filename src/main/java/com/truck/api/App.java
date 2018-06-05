package com.truck.api;

import com.mangofactory.swagger.plugin.EnableSwagger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableSwagger
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
