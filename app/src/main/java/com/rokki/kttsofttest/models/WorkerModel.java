package com.rokki.kttsofttest.models;

/**
 * Created by pcc on 15.07.2016.
 */
public class WorkerModel {

    private String name;
    private String position;
    private String department;
    private String age;
    private String salary;

    public WorkerModel(String name, String position, String department, String age, String salary) {
        this.name = name;
        this.position = position;
        this.department = department;
        this.age = age;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }
}
