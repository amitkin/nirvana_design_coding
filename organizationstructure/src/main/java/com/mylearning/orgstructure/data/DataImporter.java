package com.mylearning.orgstructure.data;

import java.util.List;

import com.mylearning.orgstructure.model.Employee;

public interface DataImporter {
    List<Employee> fromJson();
}
