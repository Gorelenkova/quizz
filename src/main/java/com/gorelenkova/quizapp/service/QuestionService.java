package com.gorelenkova.quizapp.service;

import com.gorelenkova.quizapp.dao.QuestionDao;
import com.gorelenkova.quizapp.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {
    @Autowired
    QuestionDao questionDao;
    public List<Question> getAllQuestion() {
        return questionDao.findAll();
    }
}
