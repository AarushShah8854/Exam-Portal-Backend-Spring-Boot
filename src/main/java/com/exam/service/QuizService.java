package com.exam.service;

import com.exam.model.exam.Category;
import com.exam.model.exam.Quiz;

import java.util.Set;

public interface QuizService {

    public Quiz addQuiz(Quiz quiz);

    public Quiz updateQuiz(Quiz quiz);

    public Set<Quiz> getQuizzes();

    public Set<Quiz> getQuizzesOfCategory(Category category);

    public Quiz getQuiz(Long quizId);

    public void deleteQuiz(Long quizId);

    public Set<Quiz> getActiveQuizzes();

    public Set<Quiz> getActiveQuizzesOfCategory(Category c);

}
