package com.ups.oop.repository;

import com.ups.oop.entity.Customer;
import com.ups.oop.entity.Employee;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {
}
