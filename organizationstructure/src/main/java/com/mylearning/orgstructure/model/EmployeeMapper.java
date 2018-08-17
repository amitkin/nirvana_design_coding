package com.mylearning.orgstructure.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class EmployeeMapper implements RowMapper {

    @Override
    public Employee mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        Employee employee = new Employee();
        employee.setId(resultSet.getLong("id"));
        employee.setCityName(resultSet.getString("cityName"));
        employee.setSalary(resultSet.getLong("salary"));
        employee.setFirstName(resultSet.getString("firstName"));
        employee.setSecondName(resultSet.getString("secondName"));
        employee.setManagerId(resultSet.getLong("managerId"));
        return employee;
    }
}
