package com.example.e_doctor;

import java.io.Serializable;

public class Datapovider_Exam  implements Serializable {


    private String docname, docspec, docexp, docaddres, docfees,docphone;

    public Datapovider_Exam() {
    }

    public Datapovider_Exam(String docname, String docspec, String docexp, String docaddres, String docfees, String docphone) {
        this.docname = docname;
        this.docspec = docspec;
        this.docexp = docexp;
        this.docaddres = docaddres;
        this.docfees = docfees;
        this.docphone = docphone;
    }

    public String getDocname() {
        return docname;
    }

    public void setDocname(String docname) {
        this.docname = docname;
    }

    public String getDocspec() {
        return docspec;
    }

    public void setDocspec(String docspec) {
        this.docspec = docspec;
    }

    public String getDocexp() {
        return docexp;
    }

    public void setDocexp(String docexp) {
        this.docexp = docexp;
    }

    public String getDocaddres() {
        return docaddres;
    }

    public void setDocaddres(String docaddres) {
        this.docaddres = docaddres;
    }

    public String getDocfees() {
        return docfees;
    }

    public void setDocfees(String docfees) {
        this.docfees = docfees;
    }

    public String getDocphone() {
        return docphone;
    }

    public void setDocphone(String docphone) {
        this.docphone = docphone;
    }
}