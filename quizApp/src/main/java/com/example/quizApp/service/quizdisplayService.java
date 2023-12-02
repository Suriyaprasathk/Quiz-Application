package com.example.quizApp.service;

import com.example.quizApp.DAO.questiondao;
import com.example.quizApp.DAO.quizdisplayDAO;
import com.example.quizApp.entity.Question;
import com.example.quizApp.entity.Quiz;
//import jakarta.transaction.Transactional;
import com.example.quizApp.entity.Response;
import com.example.quizApp.entity.questionWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service

public class quizdisplayService {
    @Autowired
    quizdisplayDAO daoDisplay;
    @Autowired
    questiondao question;
    public ResponseEntity<String> getAllQuestion(String category, int numberOfQuestions, String title) {
        try {
            List<Question> questions = question.findRandomQuestionByCategory(category, numberOfQuestions);

            Quiz quiz = new Quiz();
            quiz.setTitle(title);
            quiz.setQuestions(questions);
            daoDisplay.save(quiz);
            return new ResponseEntity<>("success", HttpStatus.CREATED);
        }catch(Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>("failed",HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<List<questionWrapper>> showQuestions(Integer id) {
        try {
            Optional<Quiz> quiz = daoDisplay.findById(id);
            List<Question> questionFromDb = quiz.get().getQuestions();
            List<questionWrapper> questionForUser = new ArrayList<>();
            for (Question q : questionFromDb) {
                questionWrapper qw = new questionWrapper(q.getId(), q.getQuestion_title(), q.getOption1(), q.getOption2(), q.getOption3(), q.getOption4());
                questionForUser.add(qw);
            }
            return new ResponseEntity<>(questionForUser, HttpStatus.OK);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);
        }

    public ResponseEntity<Integer> calculate(Integer id, List<Response> responses) {
       try {
           Quiz quiz = daoDisplay.findById(id).get();
           List<Question> questions = quiz.getQuestions();
           int right = 0;
           int i = 0;
           for (Response response : responses) {
               if (response.getResponse().equals(questions.get(i).getRight_answer()))
                   right++;

               i++;
           }
           return new ResponseEntity<>(right, HttpStatus.OK);
       }
       catch (Exception e){
           e.printStackTrace();
       }
       return new ResponseEntity<>(0,HttpStatus.BAD_REQUEST);
       }

}
