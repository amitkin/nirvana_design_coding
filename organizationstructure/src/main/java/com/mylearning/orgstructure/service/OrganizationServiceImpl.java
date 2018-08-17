package com.mylearning.orgstructure.service;

import java.util.List;

import com.mylearning.orgstructure.model.Employee;
import com.mylearning.orgstructure.model.EmployeeWithSubOrdinate;
import com.mylearning.orgstructure.repository.OrganizationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class OrganizationServiceImpl implements OrganizationService{

    @Autowired
    private OrganizationRepository organizationRepository;

    @Override
    public Employee getEmployeeById(Long id) {
        return organizationRepository.getEmployeeById(id);
    }

    @Override
    public boolean updateEmployeeManager(Employee e) {
        return organizationRepository.updateEmployeeManager(e);
    }

    @Override
    public EmployeeWithSubOrdinate getEmployeeWithMaxSubOridinates() {
        List<EmployeeWithSubOrdinate> employeeWithMaxSubOridinates = organizationRepository.getEmployeeWithSubOridinates();
        if(!employeeWithMaxSubOridinates.isEmpty()) {
            return employeeWithMaxSubOridinates.get(0);
        } else{
            return new EmployeeWithSubOrdinate();
        }
    }

    @Override
    public Long totalSalaryAllSubordinates(Long id) {
        return organizationRepository.totalSalaryAllSubordinates(id);
    }
}
