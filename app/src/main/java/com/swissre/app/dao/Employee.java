package com.swissre.app.dao;

import java.util.Set;

public class Employee {
    private int id;
    private String firstName;
    private String lastName;
    private double salary;
    private Integer managerId;
    private Set<Employee> subordinates;
    private boolean isOverpaid;
    private double differentialAmount;
    private int empToCeoDepth;
    private boolean isCeo;

    public Employee(int id, String firstName, String lastName, double salary, Integer managerId, Set<Employee> subordinates, boolean isOverpaid, double differentialAmount, int empToCeoDepth, boolean isCeo) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.salary = salary;
        this.managerId = managerId;
        this.subordinates = subordinates;
        this.isOverpaid = isOverpaid;
        this.differentialAmount = differentialAmount;
        this.empToCeoDepth = empToCeoDepth;
        this.isCeo = isCeo;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public void setManagerId(Integer managerId) {
        this.managerId = managerId;
    }

    public void setSubordinates(Set<Employee> subordinates) {
        this.subordinates = subordinates;
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public double getSalary() {
        return salary;
    }

    public Integer getManagerId() {
        return managerId;
    }

    public Set<Employee> getSubordinates() {
        return subordinates;
    }

    public boolean isOverpaid() {
        return isOverpaid;
    }

    public void setOverpaid(boolean overpaid) {
        isOverpaid = overpaid;
    }

    public double getDifferentialAmount() {
        return differentialAmount;
    }

    public void setDifferentialAmount(double differentialAmount) {
        this.differentialAmount = differentialAmount;
    }

    public int getEmpToCeoDepth() {
        return empToCeoDepth;
    }

    public void setEmpToCeoDepth(int empToCeoDepth) {
        this.empToCeoDepth = empToCeoDepth;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    public boolean isCeo() {
        return isCeo;
    }

    public void setCeo(boolean ceo) {
        isCeo = ceo;
    }

    @Override
    public String toString() {
        return "[id=" + id +
                ", firstName='" + firstName +
                ", lastName='" + lastName +
                ", salary=" + salary +
                ", managerId=" + managerId +
                ", isOverpaid=" + isOverpaid +
                ", differentialAmount=" + differentialAmount +
                ", isCEO=" + isCeo +
                ", empToCeoDepth=" + empToCeoDepth + "]\n";
    }
}
