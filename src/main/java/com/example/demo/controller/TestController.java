package com.example.demo.controller;

import com.example.demo.entity.Question;
import com.example.demo.entity.Result;
import com.example.demo.entity.TestEntity;
import com.example.demo.repository.ResultRepository;
import com.example.demo.repository.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class TestController {

    @Autowired
    private TestRepository testRepository;

    @Autowired
    private ResultRepository resultRepository;

    // API: получить все тесты
    @GetMapping("/api/tests")
    @ResponseBody
    public List<TestEntity> getTests() {
        return testRepository.findAll();
    }

    // API: получить тест по ID
    @GetMapping("/api/tests/{id}")
    @ResponseBody
    public TestEntity getTest(@PathVariable Long id) {
        return testRepository.findById(id).orElse(null);
    }

    // API: получить результаты
    @GetMapping("/api/results")
    @ResponseBody
    public List<Result> getResults() {
        return resultRepository.findAllByOrderByIdDesc();
    }

    // API: получить статистику для дашборда
    @GetMapping("/api/stats")
    @ResponseBody
    public Map<String, Object> getStats() {
        Map<String, Object> stats = new HashMap<>();
        stats.put("totalTests", testRepository.count());
        stats.put("totalParticipants", resultRepository.count());

        List<Result> results = resultRepository.findAll();
        double avgScore = results.stream()
                .mapToDouble(r -> r.getPercentage())
                .average()
                .orElse(0);
        stats.put("avgScore", Math.round(avgScore));

        return stats;
    }

    // Создать тест
    @PostMapping("/api/tests")
    @ResponseBody
    public TestEntity createTest(@RequestBody TestEntity test) {
        return testRepository.save(test);
    }

    // Сохранить результат
    @PostMapping("/api/results")
    @ResponseBody
    public Result saveResult(@RequestBody Result result) {
        return resultRepository.save(result);
    }

    // Страница прохождения теста
    @GetMapping("/take-test/{id}")
    public String takeTest(@PathVariable Long id, Model model) {
        model.addAttribute("testId", id);
        return "take-test";
    }
}