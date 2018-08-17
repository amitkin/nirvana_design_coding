package com.mylearning.orgstructure.repository;

import java.util.List;

import com.mylearning.orgstructure.model.Employee;
import com.mylearning.orgstructure.model.EmployeeMapper;
import com.mylearning.orgstructure.model.EmployeeWithSubOrdinate;
import com.mylearning.orgstructure.model.EmployeeWithSubOrdinateMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class OrganizationRepository {

    @Autowired
    @Qualifier("h2JdbcTemplate")
    private JdbcTemplate jdbcTemplate;

    private final String SQL_FIND_EMPLOYEE = "select * from employee where id = ?";
    private final String SQL_UPDATE_EMPLOYEE = "update employee set managerId = ? where id = ?";
    private final String SQL_INSERT_EMPLOYEE = "insert into employee(id, cityName, salary, firstName, secondName, managerId) values(?,?,?,?,?,?)";
    private final String SQL_EMPLOYEE_WITH_SUBORDINATES = "select * from (select b.*, count(e.id) as numSubOrdinates from "
            + "employee e\n"
            + "join employee b on b.id=e.managerid group by b.id, b.firstname) as t order by t.numSubOrdinates desc";
    private final String SQL_SALARY_ALL_SUBORDINATES = "select sum(salary) as totalsalary from employee where managerid = ?";


    public Employee getEmployeeById(Long id) {
        return (Employee) jdbcTemplate.queryForObject(SQL_FIND_EMPLOYEE, new Object[]{id} , new EmployeeMapper());
    }

    public boolean updateEmployeeManager(Employee employee) {
        return jdbcTemplate.update(SQL_UPDATE_EMPLOYEE, employee.getManagerId(), employee.getId()) > 0;
    }

    public boolean createEmployee(Employee employee) {
        return jdbcTemplate.update(SQL_INSERT_EMPLOYEE, employee.getId(), employee.getCityName(), employee.getSalary(), employee.getFirstName(), employee.getSecondName(), employee.getManagerId()) > 0;
    }

    public List<EmployeeWithSubOrdinate> getEmployeeWithSubOridinates() {
        return jdbcTemplate.query(SQL_EMPLOYEE_WITH_SUBORDINATES, new EmployeeWithSubOrdinateMapper());
    }

    public long totalSalaryAllSubordinates(Long id) {
        return jdbcTemplate.queryForObject(SQL_SALARY_ALL_SUBORDINATES, new Object[] {id}, Long.class);
    }

}
