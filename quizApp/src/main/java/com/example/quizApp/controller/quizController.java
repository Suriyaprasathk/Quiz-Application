package com.example.quizApp.controller;

import com.example.quizApp.entity.Response;
import com.example.quizApp.entity.questionWrapper;
import com.example.quizApp.service.quizdisplayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("quiz")
public class quizController {
    @Autowired
    quizdisplayService service;
    @PostMapping("create")
    public ResponseEntity<String> a(@RequestParam String category,@RequestParam int numberOfQuestions,@RequestParam String title){
        return service.getAllQuestion(category,numberOfQuestions,title);
        }

    @GetMapping("show/{id}")
    public ResponseEntity<List<questionWrapper>> b(@PathVariable Integer id){
        return service.showQuestions(id);
    }

    @PostMapping("submit/{id}")
    public ResponseEntity<Integer> c(@PathVariable Integer id,@RequestBody List<Response> responses){
        return service.calculate(id,responses);

    }
}
