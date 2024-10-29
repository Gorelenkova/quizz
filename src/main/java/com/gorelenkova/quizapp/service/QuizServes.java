package com.gorelenkova.quizapp.service;

import com.gorelenkova.quizapp.dao.QuestionDao;
import com.gorelenkova.quizapp.dao.QuizDao;
import com.gorelenkova.quizapp.model.Question;
import com.gorelenkova.quizapp.model.QuestionWrapper;
import com.gorelenkova.quizapp.model.Quiz;
import com.gorelenkova.quizapp.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizServes {
    @Autowired
    QuizDao quizDao;
    @Autowired
    QuestionDao questionDao;

    public ResponseEntity<String> createQuiz(String category, int numQ, String title) {

        List<Question> questions= questionDao.findRandomQuestionsByCategory(category,numQ);

        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestionList(questions);
        quizDao.save(quiz);
        return new ResponseEntity<>("super puper", HttpStatus.CREATED);
    }


    public ResponseEntity<List<QuestionWrapper>> getQuizQuestion(Integer id) {
       Optional<Quiz> quiz =  quizDao.findById(id);
       List<Question> questionFromDB=quiz.get().getQuestionList();
       List<QuestionWrapper> questionForUsers = new ArrayList<>();

        for (Question q: questionFromDB) {
            QuestionWrapper qw = new QuestionWrapper(q.getId(),q.getQuestionTitle(),q.getOption1(),q.getOption2(),q.getOption3(),q.getOption4());
            questionForUsers.add(qw);
        }
       
       return new ResponseEntity<>(questionForUsers,HttpStatus.OK);
    }

    public ResponseEntity<Integer> calculateResult(Integer id, List<Response> responses) {
        Quiz quiz = quizDao.findById(id).get();
        List<Question> questions = quiz.getQuestionList();
        int right = 0;
        int i = 0;
        for (Response response: responses) {
            if (response.getResponse().equals(questions.get(i).getRightAnswer()))
                right++;

            i++;
        }
        return new ResponseEntity<>(right,HttpStatus.OK);
    }
}