package com.example.demo.unsolid;

public class SalaryCalculator {
    private final SalaryCalculation salaryCalculation;

    public SalaryCalculator(SalaryCalculation salaryCalculation) {
        this.salaryCalculation = salaryCalculation;
    }

    public double calculateSalary(String name) {
        return salaryCalculation.calculateSalary(name);
    }
}
