package com.fullintel.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("com.fullintel")
@SpringBootApplication
public class FullIntelMain {
    public static void main(String[] args) {

        SpringApplication.run(FullIntelMain.class, args);

    }
}


