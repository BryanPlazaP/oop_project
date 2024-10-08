package com.ups.oop.entity;

import com.ups.oop.dto.Person;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Customer extends Person {
    private String clientCode;

    public Customer(Long id, String personId, String name, String lastName, Integer age, String clientCode) {
        super(id, personId, name, lastName, age);
        this.clientCode = clientCode;
    }

}