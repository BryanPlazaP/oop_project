package com.ups.oop.repository;

import com.ups.oop.entity.Employee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public interface employeeRepository extends CrudRepository<Employee, Long> {
}
