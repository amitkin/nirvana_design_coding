package com.mylearning.design.patterns.pluralsight.structural.adapter;

import java.util.ArrayList;
import java.util.List;

public class AdapterDemo {

    public static void main (String [] args) {
        List<Employee> employeeList = new ArrayList<>();

        EmployeeDb employeeDb = new EmployeeDb("1", "mike", "thomas", "mail1");
        employeeList.add(employeeDb);

        EmployeeLdap employeeLdap = new EmployeeLdap("2", "smith", "joe", "mail2");
        // Can't do this, so adapt it
        // employeeList.add(employeeLdap);

        EmployeeAdapterLdap employeeAdapterLdap = new EmployeeAdapterLdap(employeeLdap);
        employeeList.add(employeeAdapterLdap);

        EmployeeCsv employeeCsv = new EmployeeCsv("3,Sherlock,Holmes,mail3");
        EmployeeAdapterCsv employeeAdapterCsv = new EmployeeAdapterCsv(employeeCsv);
        employeeList.add(employeeAdapterCsv);
    }
}
