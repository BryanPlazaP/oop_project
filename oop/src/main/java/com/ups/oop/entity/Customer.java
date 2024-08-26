package com.ups.oop.entity;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Customer extends Person {
    private String CustomerCode;

    public Customer() {
        super();
    }

    public Customer(String CustomerCode, String personId, String name, String lastname, Integer age) {
        super(personId, name, lastname, age);
        this.CustomerCode = CustomerCode;
    }
}