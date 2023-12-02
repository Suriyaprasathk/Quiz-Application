package com.example.quizApp.DAO;

import com.example.quizApp.entity.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface quizdisplayDAO extends JpaRepository<Quiz,Integer> {

}
