package com.driver;

import javax.swing.text.html.parser.TagElement;

public class Student {

    private String name;
    private int age;
    private double averageScore;

    public Student(){

    }
    public Student(String name,int age,double averageScore)
    {
        this.name = name;
        this.age = age;
        this.averageScore = averageScore;

    }
    public void setName(String name)
    {
        this.name = name;
    }
    public void setAge(int age)
    {
        this.age = age;
    }

    public void setAverageScore(double averageScore)
    {
        this.averageScore = averageScore;
    }

    public String getName()
    {
        return this.name;
    }
    public int getAge()
    {
        return this.age;
    }
    public double getAverageScore()
    {
        return this.averageScore;
    }
}
