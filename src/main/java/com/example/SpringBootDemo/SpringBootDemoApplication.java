package com.example.SpringBootDemo;

import com.example.SpringBootDemo.dao.StudentRepository;
import com.example.SpringBootDemo.model.Student;
import com.example.SpringBootDemo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;

@SpringBootApplication
public class SpringBootDemoApplication {
    @Autowired
    StudentService studentService;
    final String fileName = "StudentsRecords.csv";

    public static void main(String[] args) {
        SpringApplication.run(SpringBootDemoApplication.class, args);
    }

    @Bean
    CommandLineRunner initDatabase() {
        return args -> {
            studentService.loadInitialDataFromFile(fileName);
        };
    }
}
