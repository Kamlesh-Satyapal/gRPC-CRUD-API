package com.example.grpc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
//@EnableJpaRepositories(basePackages = "com.example.grpc.repository")
//@EntityScan(basePackages = "com.example.grpc.model")
public class GrpcImplementationGate {
    public static void main(String[] args) {
        SpringApplication.run(GrpcImplementationGate.class, args);
    }
}