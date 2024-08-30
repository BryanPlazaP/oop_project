package com.ups.oop.entity;

import com.ups.oop.dto.Person;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Employee extends Person {
    private String employeeCode;
    @OneToMany(mappedBy = "employee")
    private List<Receipt> receipts = new ArrayList<>();

    public Employee() {
        super();
    }

    public Employee(String employeeCode, String personId, String name, String lastname, Integer age) {
        super(age, lastname, name, personId);
        this.employeeCode = employeeCode;
    }
}