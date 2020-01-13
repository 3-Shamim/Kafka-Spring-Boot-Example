package com.learningstufff.kafkaproducre.models;

/**
 * Created by IntelliJ IDEA.
 * User: Md. Shamim
 * Date: ১৩/১/২০
 * Time: ৩:৩০ PM
 * Email: mdshamim723@gmail.com
 */


public class Student {

    private String name;
    private String subject;
    private double gpa;

    public Student() {
    }

    public Student(String name, String subject, double gpa) {
        this.name = name;
        this.subject = subject;
        this.gpa = gpa;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public double getGpa() {
        return gpa;
    }

    public void setGpa(double gpa) {
        this.gpa = gpa;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", subject='" + subject + '\'' +
                ", gpa=" + gpa +
                '}';
    }
}
