package com.user.realm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;


@SpringBootApplication
public class RealmApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(RealmApplication.class, args);
    }
}
