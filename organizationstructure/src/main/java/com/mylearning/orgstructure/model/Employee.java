package com.mylearning.orgstructure.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
public class Employee {
    @Id
    @GeneratedValue
    private Long id;
    private String cityName;
    private Long salary;
    private String firstName;
    private String secondName;
    private Long managerId;
}
