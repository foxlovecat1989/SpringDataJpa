package com.edmoresby.springdatajpa;

import com.edmoresby.springdatajpa.book.Book;
import com.edmoresby.springdatajpa.student.Student;
import com.edmoresby.springdatajpa.student.StudentRepository;
import com.edmoresby.springdatajpa.studentidcard.StudentIdCard;
import com.github.javafaker.Faker;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class SpringDataJpaApplication {


    private Faker faker = new Faker();

    public static void main(String[] args) {
        SpringApplication.run(SpringDataJpaApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository studentRepository){
        return  args -> {
            List<Student> students = generateRandomStudents(20, studentRepository);
            studentRepository.saveAll(students);
            studentRepository.findAll().forEach(System.out::println);
        };
    }

    private List<Student> generateRandomStudents(Integer amount, StudentRepository studentRepository){
        List<Student> students = new ArrayList<>();

        for (int i = 0; i < amount; i++) {
            String firstName = faker.name().firstName();
            String lastName = faker.name().lastName();
            String email = String.format("%s%s@gmail.com", firstName, lastName);
            Integer age = faker.number().numberBetween(18, 60);
            Student student = new Student(firstName, lastName, email, age);

            StudentIdCard studentIdCard = generateRandomStudentIdCard(student);
            student.setStudentIdCard(studentIdCard);

            List<Book> books = generateRandomBooks(faker.number().numberBetween(1, 3));
            books.stream().forEach(book -> student.addBook(book));

            students.add(student);
        }

        return students;
    }

    private StudentIdCard generateRandomStudentIdCard(Student student) {
        StudentIdCard studentIdCard =
                new StudentIdCard(
                        Integer.toString(student.hashCode()),
                        student);

        return studentIdCard;
    }

    private List<Book> generateRandomBooks(Integer amount){
        List<Book> books = new ArrayList<>();
        for (int i = 0; i < amount; i++) {
            Book book = new Book(faker.book().title(), LocalDateTime.now().minusDays(faker.number().numberBetween(1L, 3000L)));
            books.add(book);
        }

        return books;
    }


}
