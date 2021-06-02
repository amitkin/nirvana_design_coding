package com.mylearning.design.patterns.pluralsight.behavioral.memento;

public class MementoDemo {

    public static void main(String[] args) {
        EmployeeCaretaker employeeCaretaker = new EmployeeCaretaker();

        Employee employee = new Employee();
        employee.setName("bob jones");
        employee.setAddress("123 north st");
        employee.setPhone("123-456-7890");

        employeeCaretaker.save(employee);
        System.out.println("After first save" + employee);

        employee.setPhone("098-765-4321");
        employeeCaretaker.save(employee);
        System.out.println("After changed phone number save: " + employee);

        employeeCaretaker.revert(employee);
        System.out.println("After first revert" + employee);

        employeeCaretaker.revert(employee);
        System.out.println("After second revert" + employee);

        employeeCaretaker.revert(employee);
        System.out.println("After third revert" + employee);
    }
}
