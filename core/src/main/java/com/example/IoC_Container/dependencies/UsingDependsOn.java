package com.example.IoC_Container.dependencies;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class UsingDependsOn {

    record Student(String name, int rollNo){};

    record Department(String name){};

    @Bean
    @DependsOn("department")
    Student student() {
        log.info("Student is used");
        return new Student("John Doe", 1);
    }

    @Bean
    Department department() {
        log.info("Department is used");
        return new Department("Java");
    }
}
