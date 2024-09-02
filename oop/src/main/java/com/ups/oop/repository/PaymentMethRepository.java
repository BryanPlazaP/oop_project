package com.ups.oop.repository;

import com.ups.oop.dto.Person;
import com.ups.oop.entity.PaymentMeth;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface PaymentMethRepository extends CrudRepository<PaymentMeth, Long> {
}
