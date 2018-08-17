package com.mylearning.orgstructure.data;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mylearning.orgstructure.boot.OrganizationStructureApplication;
import com.mylearning.orgstructure.data.EmployeeDataConfig.FieldConfig;
import com.mylearning.orgstructure.model.Employee;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JsonDataImporter implements DataImporter{

    private static final Logger log = LoggerFactory.getLogger(JsonDataImporter.class);

    private static ObjectMapper mapper = new ObjectMapper();

    @Override
    public List<Employee> fromJson() {
        List<Employee> employees = new ArrayList<>();
        try (InputStream dataMapper = OrganizationStructureApplication.class.getClassLoader().getResourceAsStream("dataMapper.json")){
            EmployeeDataConfig employeeDataConfig = mapper.readValue(dataMapper, EmployeeDataConfig.class);
            try (InputStream inputStream = OrganizationStructureApplication.class.getClassLoader().getResourceAsStream("EmployeeData.json")){
                JSONParser jsonParser = new JSONParser();
                try {
                    JSONArray jsonArray = (JSONArray)jsonParser.parse(new InputStreamReader(inputStream));
                    for (Object jsonObject : jsonArray) {
                        try {
                            int noOfRequiredFields = employeeDataConfig.getFields().size();
                            int actualFields = ((JSONObject) jsonObject).entrySet().size();
                            if (actualFields != noOfRequiredFields) {
                                log.warn("Skipping item in json : " + jsonObject);
                                throw new RuntimeException("");
                            }

                            Class employeeClass = Class.forName(employeeDataConfig.getEntityClass());
                            Employee employee = (Employee) employeeClass.newInstance();
                            for (int i = 0; i < noOfRequiredFields; i++) {
                                FieldConfig fieldConfig = employeeDataConfig.getFields().get(i);
                                try {
                                    Field field = employee.getClass().getDeclaredField(fieldConfig.getName());
                                    field.setAccessible(true);
                                    if (Long.class.getName().equals(fieldConfig.getType())) {
                                        field.set(employee, ((JSONObject) jsonObject).get(fieldConfig.getJsonName()));
                                    } else {
                                        field.set(employee, ((JSONObject) jsonObject).get(fieldConfig.getJsonName()));
                                    }
                                } catch (NoSuchFieldException e) {
                                    e.printStackTrace();
                                }
                            }
                            employees.add(employee);
                        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
                            e.printStackTrace();
                        }
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            } catch (IOException e) {
                throw new RuntimeException("Unable to read EmployeeData.json file due to " + e.getMessage(), e);
            }
        } catch (IOException e) {
            throw new RuntimeException("Unable to read dataMapper.json file due to " + e.getMessage(), e);
        }
        return employees;
    }
}
