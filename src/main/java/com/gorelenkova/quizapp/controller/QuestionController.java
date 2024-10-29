package com.gorelenkova.quizapp.controller;

import com.gorelenkova.quizapp.model.Question;
import com.gorelenkova.quizapp.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("question")
public class QuestionController{

    @Autowired
    QuestionService questionService;
    @GetMapping("allQuestion")
    public ResponseEntity<List<Question>> getAllQuestion(){

        return  questionService.getAllQuestion();
    }
    @GetMapping("category/{category}")
    public ResponseEntity<List<Question>> getQuestionByCategory(@PathVariable String category){
            return questionService.getQuestionByCategory(category);
    }


    @PostMapping("add")
    public ResponseEntity<String> addQuestion(@RequestBody Question question) {
        return questionService.addQuestion(question);
    }


    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deleteQuestion(@PathVariable Integer id){
       return questionService.deleteQuestion(id);
    }

    @PutMapping("update/{id}")
    public ResponseEntity<Question> putQuestion(@PathVariable Integer id,@RequestBody Question question){
        return questionService.putQuestion(id,question);
    }

}
