package com.ups.oop.repository;

import com.ups.oop.entity.Employee;
import com.ups.oop.entity.Receipt;
import org.springframework.data.repository.CrudRepository;

public interface receiptRepository extends CrudRepository<Receipt, Long> {
}
