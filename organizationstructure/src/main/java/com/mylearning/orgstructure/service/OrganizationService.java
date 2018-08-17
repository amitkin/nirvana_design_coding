package com.mylearning.orgstructure.service;

import com.mylearning.orgstructure.model.Employee;
import com.mylearning.orgstructure.model.EmployeeWithSubOrdinate;

public interface OrganizationService {

    Employee getEmployeeById(Long id);

    boolean updateEmployeeManager(Employee e);

    EmployeeWithSubOrdinate getEmployeeWithMaxSubOridinates();

    Long totalSalaryAllSubordinates(Long id);
}
