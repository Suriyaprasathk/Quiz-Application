package com.example.quizApp.controller;

import com.example.quizApp.entity.Question;
import com.example.quizApp.service.quizservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("Question")
public class questioncontroller {


    @Autowired
    quizservice Quizservice;
    @GetMapping("allquestion")
    public ResponseEntity<List<Question>> a(){

        return Quizservice.getAllQuestions();
    }

    @GetMapping("category/{category}")
    public ResponseEntity<List<Question>> b(@PathVariable String category)
    {

        return Quizservice.getQuestionByCategory(category);
    }
    @PostMapping("add")
    public ResponseEntity<String> c(@RequestBody Question question)
    {
        return Quizservice.addto(question);

    }


    @DeleteMapping("delete")
    public ResponseEntity<String> d(@RequestBody Question question)
    {
        return Quizservice.deleteto(question);
    }

    @PutMapping("update")
    public ResponseEntity<String> e(@RequestBody Question question){
        return Quizservice.updateto(question);
    }


}
