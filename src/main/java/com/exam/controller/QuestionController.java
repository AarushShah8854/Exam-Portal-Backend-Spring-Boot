package com.exam.controller;

import com.exam.model.exam.Question;
import com.exam.model.exam.Quiz;
import com.exam.service.QuestionService;
import com.exam.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/question")
@CrossOrigin("*")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @Autowired
    private QuizService quizService;

    //Add a question
    @PostMapping("/")
    public ResponseEntity<Question> add(@RequestBody Question question) {
        return ResponseEntity.ok(this.questionService.addQuestion(question));
    }

    //Update a question
    @PutMapping("/")
    public ResponseEntity<Question> update(@RequestBody Question question) {
        return ResponseEntity.ok(this.questionService.updateQuestion(question));
    }

    //Get all questions
    @GetMapping("/")
    public ResponseEntity<?> getQuestions() {
        return ResponseEntity.ok(this.questionService.getQuestions());
    }

    //Get a particular question
    @GetMapping("/{quesId}")
    public Question get(@PathVariable("quesId") Long quesId) {
        return this.questionService.getQuestion(quesId);
    }

    //Get all questions of a particular quiz
    @GetMapping("/quiz/{qid}")
    public ResponseEntity<?> getQuestionsOfQuiz(@PathVariable("qid") Long qid) {
        Quiz quiz = this.quizService.getQuiz(qid);
        Set<Question> questions = quiz.getQuestions();
        List list = new ArrayList(questions);
        if(list.size()>Integer.parseInt(quiz.getNumOfQuestions())) {
            list = list.subList(0, Integer.parseInt(quiz.getNumOfQuestions())+1);
        }
        Collections.shuffle(list);
        return ResponseEntity.ok(list);
    }

    //Delete a particular question
    @DeleteMapping("/{quesId}")
    public void delete(@PathVariable("quesId") Long quesId) {
        this.questionService.deleteQuestion(quesId);
    }
}
