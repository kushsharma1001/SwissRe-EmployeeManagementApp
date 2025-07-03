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

    private Employee(Builder builder) {
        this.id = builder.id;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.salary = builder.salary;
        this.managerId = builder.managerId;
        this.subordinates = builder.subordinates;
        this.isOverpaid = builder.isOverpaid;
        this.differentialAmount = builder.differentialAmount;
        this.empToCeoDepth = builder.empToCeoDepth;
        this.isCeo = builder.isCeo;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
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

        public Builder id(int id) {
            this.id = id;
            return this;
        }
        public Builder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }
        public Builder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }
        public Builder salary(double salary) {
            this.salary = salary;
            return this;
        }
        public Builder managerId(Integer managerId) {
            this.managerId = managerId;
            return this;
        }
        public Builder subordinates(Set<Employee> subordinates) {
            this.subordinates = subordinates;
            return this;
        }
        public Builder isOverpaid(boolean isOverpaid) {
            this.isOverpaid = isOverpaid;
            return this;
        }
        public Builder differentialAmount(double differentialAmount) {
            this.differentialAmount = differentialAmount;
            return this;
        }
        public Builder empToCeoDepth(int empToCeoDepth) {
            this.empToCeoDepth = empToCeoDepth;
            return this;
        }
        public Builder isCeo(boolean isCeo) {
            this.isCeo = isCeo;
            return this;
        }
        public Employee build() {
            return new Employee(this);
        }
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
