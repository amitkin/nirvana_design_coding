package com.mylearning.orgstructure.controller;

import com.mylearning.orgstructure.model.Employee;
import com.mylearning.orgstructure.model.EmployeeWithSubOrdinate;
import com.mylearning.orgstructure.service.OrganizationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value = "/organization/employee")
public class OrganizationController {

    @Autowired
    private OrganizationService organizationService;

    @GetMapping("/{id}")
    public Employee getEmployeeById(@PathVariable Long id){
        return organizationService.getEmployeeById(id);
    }

    @PutMapping("/manager")
    public boolean updateEmployeeManager(@RequestBody Employee e){
        return organizationService.updateEmployeeManager(e);
    }

    @GetMapping("/max/subordinates")
    public EmployeeWithSubOrdinate getEmployeeWithMaxSubOridinates(){
        return organizationService.getEmployeeWithMaxSubOridinates();
    }

    @GetMapping("/salary/all/subordinates/{id}")
    public Long totalSalaryAllSubordinates(@PathVariable Long id){
        return organizationService.totalSalaryAllSubordinates(id);
    }

    @GetMapping("/common/manager")
    public Employee getCommonManager(@RequestParam("id1") Long id1, @RequestParam("id2") Long id2){
        return organizationService.getCommonManager(id1, id2);
    }
}
