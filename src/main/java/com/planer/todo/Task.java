package com.planer.todo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "tasks")
public class Task {

    @Id
    public String id;
    public String title;
    public boolean completed;

    public Task(){
    }

    public Task(String title, boolean completed) {
        this.title = title;
        this.completed = completed;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public boolean isCompleted() {
        return completed;
    }
    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    @Override
    public String toString() {
        return "Task{"
                 + "[id=" + id + ", title=" + title + ", completed=" + completed + "]}";
    }
}
