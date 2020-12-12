package com.example.whatatodo;

public class Tasks {
    private int id;
    private String task;
    private String date;
    private int status;

    public Tasks (String task, String date, int status){
        this.task = task;
        this.date = date;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Tasks (int id, String task, String date, int status){
        this.id = id;
        this.task = task;
        this.date = date;
        this.status = status;
    }


}
