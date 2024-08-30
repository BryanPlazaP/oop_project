package com.ups.oop.repository;

import com.ups.oop.entity.Store;
import org.springframework.data.repository.CrudRepository;

public interface StoreRepository extends CrudRepository<Store, Long> {
}
