package com.example.e_doctor;

public class Datapovider_Feed {


    private String name,feedback;

    public Datapovider_Feed() {
    }

    public Datapovider_Feed(String name, String feedback) {
        this.name = name;
        this.feedback = feedback;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }
}