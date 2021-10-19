package com.edmoresby.springdatajpa;

import com.edmoresby.springdatajpa.student.Student;
import com.edmoresby.springdatajpa.student.StudentRepository;
import com.github.javafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class SpringDataJpaApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringDataJpaApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository studentRepository){
        return  args -> {
            List<Student> students = generateRandomStudents(20);
            studentRepository.saveAll(students);
            studentRepository.findAll().forEach(System.out::println);
        };
    }

    private  List<Student> generateRandomStudents(Integer amount){
        List<Student> students = new ArrayList<>();
        Faker faker = new Faker();
        for (int i = 0; i < amount; i++) {
            String firstName = faker.name().firstName();
            String lastName = faker.name().lastName();
            String email = String.format("%s%s@gmail.com", firstName, lastName);
            Integer age = faker.number().numberBetween(18, 60);
            students.add(new Student(firstName, lastName, email, age));
        }

        return students;
    }


}
