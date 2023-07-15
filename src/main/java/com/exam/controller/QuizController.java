package com.exam.controller;

import com.exam.model.exam.Category;
import com.exam.model.exam.Quiz;
import com.exam.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@CrossOrigin("*")
@RequestMapping("/quiz")
public class QuizController {

    @Autowired
    private QuizService quizService;

    //Add quiz
    @PostMapping("/")
    public ResponseEntity<Quiz> addQuiz(@RequestBody Quiz quiz) {
        return ResponseEntity.ok(this.quizService.addQuiz(quiz));
    }

    //Update Quiz
    @PutMapping("/")
    public ResponseEntity<Quiz> updateQuiz(@RequestBody Quiz quiz) {
        return ResponseEntity.ok(this.quizService.updateQuiz(quiz));
    }

    //Get all quizzes
    @GetMapping("/")
    public ResponseEntity<?> quizzes() {
        return ResponseEntity.ok(this.quizService.getQuizzes());
    }

    //Get the quizzes of a particular category
    @GetMapping("/category/{cid}")
    public ResponseEntity<?> getQuizzesOfCategory(@PathVariable("cid") Long cid) {
        System.out.println("inside getQuizzesOfCategory");
        Category category = new Category();
        category.setCid(cid);
        return ResponseEntity.ok(this.quizService.getQuizzesOfCategory(category));

    }

    //Get a particular quiz
    @GetMapping("/{qId}")
    public Quiz getQuiz(@PathVariable("qId") Long qId) {
        return this.quizService.getQuiz(qId);
    }

    //Delete a quiz
    @DeleteMapping("/{qId}")
    public void deleteQuiz(@PathVariable("qId") Long qId) {
        this.quizService.deleteQuiz(qId);
    }

    //Get all Active Quizzes
    @GetMapping("/active")
    public Set<Quiz> getActiveQuizzes() {
        return this.quizService.getActiveQuizzes();
    }

    //Get all Active Quizzes of a particular Category
    @GetMapping("/category/active/{cid}")
    public Set<Quiz> getActiveQuizzesOfCategory(@PathVariable("cid") Long cid) {
        Category category = new Category();
        category.setCid(cid);
        return this.quizService.getActiveQuizzesOfCategory(category);
    }
}
