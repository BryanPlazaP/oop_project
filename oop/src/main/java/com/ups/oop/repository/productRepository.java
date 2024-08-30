package com.ups.oop.repository;

import com.ups.oop.entity.Employee;
import com.ups.oop.entity.Product;
import org.springframework.data.repository.CrudRepository;

public interface productRepository extends CrudRepository<Product, Long> {
}
