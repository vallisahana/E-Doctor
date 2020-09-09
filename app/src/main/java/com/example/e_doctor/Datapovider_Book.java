package com.example.e_doctor;

import java.io.Serializable;

public class Datapovider_Book implements Serializable {


    private String name,date,time,problem,phone,docname,docphone;

    public Datapovider_Book() {
    }

    public Datapovider_Book(String name, String date, String time, String problem, String phone, String docname, String docphone) {
        this.name = name;
        this.date = date;
        this.time = time;
        this.problem = problem;
        this.phone = phone;
        this.docname = docname;
        this.docphone = docphone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getProblem() {
        return problem;
    }

    public void setProblem(String problem) {
        this.problem = problem;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDocname() {
        return docname;
    }

    public void setDocname(String docname) {
        this.docname = docname;
    }

    public String getDocphone() {
        return docphone;
    }

    public void setDocphone(String docphone) {
        this.docphone = docphone;
    }
}