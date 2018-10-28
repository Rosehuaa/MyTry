package com.example.honey.mytry.entity;

import java.util.ArrayList;

public class Question {


    private int questionId;//题目id

    private String type;    //单选多选标识

    private String content;   //题目内容

    private ArrayList<Answer> answers;//选项list

    private int que_state;//本题是否解答

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int quesitionId) {
        this.questionId = quesitionId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public ArrayList<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(ArrayList<Answer> answers) {
        this.answers = answers;
    }

    public int getQue_state() {
        return que_state;
    }

    public void setQue_state(int que_state) {
        this.que_state = que_state;
    }
}
