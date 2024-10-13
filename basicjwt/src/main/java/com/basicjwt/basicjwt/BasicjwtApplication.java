package com.basicjwt.basicjwt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication(scanBasePackages = "com.basicjwt")
public class BasicjwtApplication {

    public static void main(String[] args) {
        SpringApplication.run(BasicjwtApplication.class, args);        
	}
}
