package edu.cpp.cs499.androidbasics;

import android.provider.ContactsContract;
import android.util.Log;

/**
 * Created by yusun on 1/11/16.
 */
public class QuizQuestion {

    private String title;
    private boolean correctAnswer;

    public QuizQuestion(String title, boolean correctAnswer) {
        this.title = title;
        this.correctAnswer = correctAnswer;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(boolean correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

}
