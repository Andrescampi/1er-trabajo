package com.example.demo.unsolid;

public class PartTimeEmployee implements Employee {
    private String name;
    private String department;

    public PartTimeEmployee(String name, String department) {
        this.name = name;
        this.department = department;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDepartment() {
        return department;
    }
}
