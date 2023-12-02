package com.example.quizApp.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Question{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String question_title;
    private String option1;
    private String option2;

    private String option3;
    private String option4;
    private String right_answer;
    private String difficulty_level;
    private String category;


}
