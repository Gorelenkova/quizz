package com.gorelenkova.quizapp.service;

import com.gorelenkova.quizapp.dao.QuestionDao;
import com.gorelenkova.quizapp.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {
    @Autowired
    QuestionDao questionDao;
    public ResponseEntity<List<Question>> getAllQuestion() {
        try {
            new ResponseEntity<>(questionDao.findAll(), HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<List<Question>> getQuestionByCategory(String category) {
        try {
            new ResponseEntity<>(questionDao.findByCategory(category),HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);

    }

    public ResponseEntity<String> addQuestion(Question question) {
        try {
            new ResponseEntity<>( questionDao.save(question),HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }
       return new ResponseEntity<>("Success", HttpStatus.CREATED);

    }

    public ResponseEntity<String> deleteQuestion(Integer id) {
        try {
            questionDao.deleteById(id);
            return new ResponseEntity<>("Question deleted", HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Failed to delete question", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    public ResponseEntity<Question> putQuestion(Integer id, Question question) {
        return questionDao.findById(id)
                .map(existingQuestion -> {
                    existingQuestion.setQuestionTitle(question.getQuestionTitle());
                    existingQuestion.setOption1(question.getOption1());
                    existingQuestion.setOption2(question.getOption2());
                    existingQuestion.setOption3(question.getOption3());
                    existingQuestion.setOption4(question.getOption4());
                    existingQuestion.setRightAnswer(question.getRightAnswer());
                    existingQuestion.setDifficultyLevel(question.getDifficultyLevel());
                    existingQuestion.setCategory(question.getCategory());
                    return questionDao.save(existingQuestion);
                })
                .map(updatedQuestion -> new ResponseEntity<>(updatedQuestion, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


}
