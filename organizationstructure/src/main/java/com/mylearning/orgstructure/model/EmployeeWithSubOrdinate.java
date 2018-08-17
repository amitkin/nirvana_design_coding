package com.mylearning.orgstructure.model;

import lombok.Data;

@Data
public class EmployeeWithSubOrdinate {
    Long id;
    String cityName;
    Long salary;
    String firstName;
    String secondName;
    Long managerId;
    Long numSubOrdinates;
}
