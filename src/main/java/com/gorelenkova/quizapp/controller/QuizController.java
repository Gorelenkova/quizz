package com.gorelenkova.quizapp.controller;


import com.gorelenkova.quizapp.model.Question;
import com.gorelenkova.quizapp.model.QuestionWrapper;
import com.gorelenkova.quizapp.model.Response;
import com.gorelenkova.quizapp.service.QuestionService;
import com.gorelenkova.quizapp.service.QuizServes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("quiz")
public class QuizController {
    @Autowired
    QuizServes quizServes;
    @PostMapping("create")
    public ResponseEntity<String> createQuiz(@RequestParam String category, @RequestParam int numQ, @RequestParam String title){
        return quizServes.createQuiz(category,numQ,title);
    }
    @GetMapping("get/{id}")
    public ResponseEntity<List<QuestionWrapper>> getQuizQuestion(@PathVariable Integer id){
         return quizServes.getQuizQuestion(id);
    }

    @PostMapping("submit/{id}")
    public ResponseEntity<Integer> submitQuiz(@PathVariable Integer id,@RequestBody List<Response> responses){
        return quizServes.calculateResult(id,responses);
    }

}
