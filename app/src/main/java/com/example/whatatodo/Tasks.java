package com.example.whatatodo;

@SuppressWarnings({"ALL", "unused"})
public class Tasks {
    private int id;
    private String task;
    private String date;
    private boolean status;

    public Tasks (String task, String date, boolean status){
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

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public boolean getStatus(int id){
        this.id = id;
        return this.status;
    }

    public Tasks (int id, String task, String date, boolean status){
        this.id = id;
        this.task = task;
        this.date = date;
        this.status = status;
    }


}
