/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Chapter1;

import java.util.Comparator;

/**
 *
 * @author Mahmoud_Abusaqer
 */
public abstract class Student implements Comparator<Student> {

    protected int id;
    protected String name;
    protected String major;
    protected double grade;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }

    public String toString() {
        return "ID: " + id + ", Name: " + name + ", Major: " + major + ", Grade: " + grade;
    }

    @Override
    public int compare(Student emp1, Student emp2) {
        return (int) (emp2.getGrade() - emp1.getGrade());
    }
}
