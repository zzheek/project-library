package com.project_library;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class ProjectLibraryApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProjectLibraryApplication.class, args);
    }

}
