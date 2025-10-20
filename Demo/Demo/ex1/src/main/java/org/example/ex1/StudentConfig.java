package org.example.ex1;

import org.example.ex1.service.StudentService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ComponentScan;

@Configuration
@ComponentScan(basePackages = "org.example.ex1.service")
public class StudentConfig {

    @Bean
    public StudentService studentService() {
        return new StudentService();
    }
}