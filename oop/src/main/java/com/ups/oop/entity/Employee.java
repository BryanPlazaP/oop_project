package com.ups.oop.entity;


import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
public class Employee extends Person {
    private String employeeCode;

    public Employee() {
        super();
    }

    public Employee(String employeeCode, String personId, String name, String lastname, Integer age) {
        super(personId, name, lastname, age);
        this.employeeCode = employeeCode;
    }
}