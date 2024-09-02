package com.ups.oop.repository;

import com.ups.oop.entity.Product;
import com.ups.oop.entity.Store;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface StoreRepository extends CrudRepository<Store, Long> {
}
