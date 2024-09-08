package com.pocket.outbound;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EntityScan(basePackages = "com.pocket")
@ComponentScan(basePackages = "com.pocket")
public class OutboundApplication {

    public static void main(String[] args) {
        SpringApplication.run(OutboundApplication.class, args);
    }

}