package com.xworkz.library_mangement;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class LibraryMangementApplication {

    public static void main(String[] args) {
        log.info("Starting Library Management Application...");
        SpringApplication.run(LibraryMangementApplication.class, args);
        log.info("Library Management Application started successfully!");
    }
}
