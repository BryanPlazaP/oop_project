package com.ups.oop.repository;

import com.ups.oop.dto.Person;
import com.ups.oop.entity.Customer;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CustomerRepository extends CrudRepository<Customer, Long> {
    Optional<Customer> findByCustomerId(String personId);

}
