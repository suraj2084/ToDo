package com.example.todo;

public class ToDoModel {


    private String Work;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    private String number;
    public String getWork() {
        return Work;
    }

    public void setWork(String work) {
        Work = work;
    }

    public ToDoModel(String work) {
        Work = work;
    }

    public ToDoModel() {
    }
}
