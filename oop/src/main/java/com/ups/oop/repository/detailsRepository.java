package com.ups.oop.repository;

import com.ups.oop.dto.PersonDTO;
import com.ups.oop.entity.Person;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public interface detailsRepository extends CrudRepository<Person, Long> {


}
