package com.example.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.demo.dao")
public class DemoApplication {

    public static void main(String[] args) {
        System.out.println("启动 Spring Boot...");
        SpringApplication.run(DemoApplication.class, args);
    }

}
