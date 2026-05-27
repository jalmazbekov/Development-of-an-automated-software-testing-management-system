package com.example.demo;

import com.example.demo.entity.Question;
import com.example.demo.entity.TestEntity;
import com.example.demo.repository.TestRepository;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.util.Arrays;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private TestRepository testRepository;

    @Autowired
    private UserService userService;

    @Override
    public void run(String... args) throws Exception {
        // Создаём роли и пользователей
        userService.saveTeacher("teacher", "123", "Иван Петров");
        userService.saveStudent("student", "123", "Алексей Сидоров");

        System.out.println("✅ Пользователи созданы:");
        System.out.println("   Преподаватель: teacher / 123");
        System.out.println("   Студент: student / 123");

        if (testRepository.count() == 0) {
            // Тест 1: Java Basics
            TestEntity javaTest = new TestEntity();
            javaTest.setTitle("Java Programming Basics");
            javaTest.setDescription("Test your knowledge of core Java concepts");

            Question q1 = new Question();
            q1.setText("What is the size of int in Java?");
            q1.setOptionA("16 bits");
            q1.setOptionB("32 bits");
            q1.setOptionC("64 bits");
            q1.setOptionD("Depends on platform");
            q1.setCorrectAnswer("B");

            Question q2 = new Question();
            q2.setText("Which keyword is used to inherit a class in Java?");
            q2.setOptionA("super");
            q2.setOptionB("this");
            q2.setOptionC("extends");
            q2.setOptionD("implements");
            q2.setCorrectAnswer("C");

            Question q3 = new Question();
            q3.setText("What is JVM?");
            q3.setOptionA("Java Variable Machine");
            q3.setOptionB("Java Virtual Machine");
            q3.setOptionC("Just Virtual Memory");
            q3.setOptionD("Java Visual Machine");
            q3.setCorrectAnswer("B");

            javaTest.setQuestions(Arrays.asList(q1, q2, q3));
            testRepository.save(javaTest);

            // Тест 2: Spring Boot
            TestEntity springTest = new TestEntity();
            springTest.setTitle("Spring Boot Fundamentals");
            springTest.setDescription("Test your Spring Boot knowledge");

            Question sq1 = new Question();
            sq1.setText("What annotation is used to make a class a REST controller?");
            sq1.setOptionA("@Controller");
            sq1.setOptionB("@RestController");
            sq1.setOptionC("@Service");
            sq1.setOptionD("@Component");
            sq1.setCorrectAnswer("B");

            Question sq2 = new Question();
            sq2.setText("Which dependency is used for Spring Data JPA?");
            sq2.setOptionA("spring-boot-starter-web");
            sq2.setOptionB("spring-boot-starter-data-jpa");
            sq2.setOptionC("spring-boot-starter-security");
            sq2.setOptionD("spring-boot-starter-test");
            sq2.setCorrectAnswer("B");

            springTest.setQuestions(Arrays.asList(sq1, sq2));
            testRepository.save(springTest);

            System.out.println("✅ Sample tests loaded!");
        }
    }
}