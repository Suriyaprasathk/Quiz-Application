package com.example.quizApp.DAO;

import com.example.quizApp.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface questiondao extends JpaRepository<Question, Integer> {
    List<Question> findByCategory(String category);

    @Query(value = "SELECT * FROM Question q WHERE category = :category ORDER BY RAND() LIMIT :numberOfQuestion", nativeQuery = true)
    List<Question> findRandomQuestionByCategory(String category, int numberOfQuestion);
}
