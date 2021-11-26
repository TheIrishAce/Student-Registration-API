/*
 *   Created by Max Delaney
 *   24/11/2021
 *   Tutorial Followed: Amigoscode.
 *
 *   Config File / Create initial students.
 *   S05
 */

package com.delaney.student_dashboard.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import static java.time.Month.JANUARY;

@Configuration //Makes this a configuration class
public class StudentConfig {

    @Bean   //Creates a bean to be handled by Spring.
    CommandLineRunner commandLineRunner(StudentRepository repository) {
        return args -> {
            Student max_delaney = new Student(
                    "Max Delaney",
                    "maxdelaney16@gmail.com",
                    LocalDate.of(2000, JANUARY, 5)
            );

            Student bob_ross = new Student(
                    "Bob Ross",
                    "brossie@gmail.com",
                    LocalDate.of(1982, JANUARY, 5)
            );

            //Save to database
            repository.saveAll(
                List.of(max_delaney, bob_ross)
            );
        };
    }
}
