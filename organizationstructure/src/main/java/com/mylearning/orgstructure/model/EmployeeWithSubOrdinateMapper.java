package com.mylearning.orgstructure.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class EmployeeWithSubOrdinateMapper implements RowMapper {

    @Override
    public EmployeeWithSubOrdinate mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        EmployeeWithSubOrdinate employee = new EmployeeWithSubOrdinate();
        employee.setId(resultSet.getLong("id"));
        employee.setCityName(resultSet.getString("cityName"));
        employee.setSalary(resultSet.getLong("salary"));
        employee.setFirstName(resultSet.getString("firstName"));
        employee.setSecondName(resultSet.getString("secondName"));
        employee.setManagerId(resultSet.getLong("managerId"));
        employee.setNumSubOrdinates(resultSet.getLong("numSubOrdinates"));
        return employee;
    }
}
