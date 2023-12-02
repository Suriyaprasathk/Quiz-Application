package com.example.quizApp.service;

import com.example.quizApp.DAO.questiondao;
import com.example.quizApp.entity.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class quizservice {
    @Autowired
    questiondao Questiondao;

    public ResponseEntity<List<Question>> getAllQuestions() {
        try {
            return new ResponseEntity<>(Questiondao.findAll(), HttpStatus.OK);
        }catch(Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<List<Question>> getQuestionByCategory(String category) {
        try {
            return new ResponseEntity<>(Questiondao.findByCategory(category), HttpStatus.OK);
        }catch(Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);

    }

    public ResponseEntity<String> addto(Question question) {
        try {
            Questiondao.save(question);
            return new ResponseEntity<>("successfully added", HttpStatus.CREATED);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>("failed to add",HttpStatus.BAD_REQUEST);
    }


    public ResponseEntity<String> deleteto(Question question) {
        try {
            Questiondao.delete(question);
            return new ResponseEntity<>("successfully deleted",HttpStatus.OK);
        }catch(Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>("failed to delete",HttpStatus.BAD_REQUEST);
    }

    public  ResponseEntity<String> updateto(Question question) {
        try{

            Question availablequestion = Questiondao.findById(question.getId()).orElseThrow(()->new RuntimeException("id not found"));


            availablequestion.setCategory(question.getCategory());
            availablequestion.setDifficulty_level(question.getDifficulty_level());
            availablequestion.setOption1(question.getOption1());
            availablequestion.setOption2(question.getOption2());
            availablequestion.setOption3(question.getOption3());
            availablequestion.setOption4(question.getOption4());
            availablequestion.setQuestion_title(question.getQuestion_title());
            availablequestion.setRight_answer(question.getRight_answer());

            Questiondao.save(availablequestion);

            return new ResponseEntity<>("successfully updated",HttpStatus.OK);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("Error updating the question",HttpStatus.BAD_REQUEST);
    }
}
