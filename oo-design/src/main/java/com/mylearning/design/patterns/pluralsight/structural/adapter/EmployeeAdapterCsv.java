package com.mylearning.design.patterns.pluralsight.structural.adapter;

public class EmployeeAdapterCsv implements Employee {

    private EmployeeCsv instance;

    public EmployeeAdapterCsv(EmployeeCsv instance) {
        this.instance = instance;
    }

    @Override
    public String getId() {
        return String.valueOf(instance.getId());
    }

    @Override
    public String getFirstName() {
        return instance.getFirstname();
    }

    @Override
    public String getLastName() {
        return instance.getLastname();
    }

    @Override
    public String getEmail() {
        return instance.getEmailAddress();
    }
}
