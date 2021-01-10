package com.example.tolistproject;

public class Model {
    private String task,description,id,date;
    public Model(){
    }
    public Model(String task, String description, String id, String date) {
        this.task = task;
        this.description = description;
        this.id = id;
        this.date = date;
    }

    public String getTask() {
        return task;
    }

    public String getDescription() {
        return description;
    }

    public String getId() {
        return id;
    }

    public String getDate() {
        return date;
    }
}
