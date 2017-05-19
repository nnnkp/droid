package com.example.npraj1.employdb;

/**
 * Created by npraj1 on 5/16/2017.
 */

public class Employee {
    private String employeeName;
    private String employeeAge;
    private String employeeDomain;
    private String employeeProject;

    private boolean isOpen;

    public Employee(String employeeName, String employeeAge, String employeeDomain, String employeeProject) {
        this.employeeName = employeeName;
        this.employeeAge = employeeAge;
        this.employeeDomain = employeeDomain;
        this.employeeProject = employeeProject;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getEmployeeAge() {
        return employeeAge;
    }

    public void setEmployeeAge(String employeeAge) {
        this.employeeAge = employeeAge;
    }

    public String getEmployeeDomain() {
        return employeeDomain;
    }

    public void setEmployeeDomain(String employeeDomain) {
        this.employeeDomain = employeeDomain;
    }

    public String getEmployeeProject() {
        return employeeProject;
    }

    public void setEmployeeProject(String employeeProject) {
        this.employeeProject = employeeProject;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }
}
