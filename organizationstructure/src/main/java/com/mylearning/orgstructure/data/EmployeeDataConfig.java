package com.mylearning.orgstructure.data;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class EmployeeDataConfig {

    private String entityClass;
    private List<FieldConfig> fields;

    @Getter
    @Setter
    @ToString
    public static class FieldConfig {
        private String name;
        private String jsonName;
        private String type;
    }
}
