package com.mylearning.orgstructure.boot;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeNode {
    Long id;
    String cityName;
    Long salary;
    String firstName;
    String secondName;
    Long managerId;
    List<EmployeeNode> subordinates;

    public EmployeeNode(Long id, String cityName, Long salary, String firstName, String secondName, Long managerId) {
        this.id = id;
        this.cityName = cityName;
        this.salary = salary;
        this.firstName = firstName;
        this.secondName = secondName;
        this.managerId = managerId;
    }
}
