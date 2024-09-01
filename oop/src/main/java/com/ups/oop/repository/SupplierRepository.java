package com.ups.oop.repository;

import com.ups.oop.entity.Store;
import com.ups.oop.entity.Supplier;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface SupplierRepository extends CrudRepository<Supplier, Long> {
    Optional<Supplier> findBySupplierId(String supplierId);
}
