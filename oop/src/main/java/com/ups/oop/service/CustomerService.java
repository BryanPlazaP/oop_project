package com.ups.oop.service;

import com.ups.oop.dto.CustomerDTO;
import com.ups.oop.dto.PersonDTO;
import com.ups.oop.entity.Customer;
import com.ups.oop.repository.CustomerRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;
    private List<CustomerDTO> customerDTOList = new ArrayList<>();

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public ResponseEntity createCustomer(CustomerDTO customerDTO) {
        String customerId = customerDTO.getId();
        //check repository if record exist
        Optional<Customer> customerOptional = customerRepository.findById(Long.valueOf(customerId));
        if(customerOptional.isPresent()) {
            String errorMessage = "Customer with id " + customerId + " already exists";
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
        } else {
            //Before Register Person, name and lastname are present
            if(customerDTO.getName().contains(" ")) {
                //Build Person and save in Repository
                Customer customerRecord = new Customer();
                customerRecord.setClientCode(customerId);
                String[] nameStrings = customerDTO.getName().split(" ");
                String name = nameStrings[0];
                String lastname = nameStrings[1];
                customerRecord.setName(name);
                customerRecord.setLastName(lastname);
                customerRecord.setAge(customerDTO.getAge());
                customerRepository.save(customerRecord);
                return ResponseEntity.status(HttpStatus.OK).body(customerDTO);
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Person name must contain two strings separated by a whitespace");
            }
        }
    }

    public ResponseEntity getAllCustomer() {
        List<CustomerDTO> customerList = getCustomer();
        if(customerList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Person List not found");
        }
        return ResponseEntity.status(HttpStatus.OK).body(customerList);
    }

    public List<CustomerDTO> getCustomer() {
        Iterable<Customer> personIterable = customerRepository.findAll();
        List<CustomerDTO> customerList = new ArrayList<>();
        for(Customer cus : personIterable) {
            CustomerDTO customer = new CustomerDTO();
            customer.setId(cus.getPersonId());
            customer.setName(cus.getName() + "-" + cus.getLastName());
            customer.setAge(cus.getAge());
            customerList.add(customer);
        }
        return customerList;
    }

    public ResponseEntity getCustomerById(String customerId) {
        Optional<Customer> customerOptional = customerRepository.findById(Long.valueOf(customerId));
        if(customerOptional.isPresent()) {
            //if record was found
            Customer customerFound = customerOptional.get();
            CustomerDTO customer = new CustomerDTO(customerFound.getPersonId(),
                    customerFound.getName() + "-" + customerFound.getLastName(),
                    customerFound.getAge(), customerFound.getClientCode());
            return ResponseEntity.status(HttpStatus.OK).body(customer);
        } else {
            //if record wasn't found
            String errorMessage = "Person with id " + customerId + " not found";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
        }
    }

    public ResponseEntity updateCustomer(CustomerDTO customerDTO) {
        String requestId = customerDTO.getId();
        //check repository if record exist
        Optional<Customer> customerOptional = customerRepository.findById(Long.valueOf(requestId));
        if(customerOptional.isPresent()) {
            //If record exists, then perform Update
            Customer customer = customerOptional.get();
            if(customerDTO.getName().contains(" ")) {
                //Build Person and save in Repository
                customer.setPersonId(requestId);
                String[] nameStrings = customerDTO.getName().split(" ");
                String name = nameStrings[0];
                String lastname = nameStrings[1];
                customer.setName(name);
                customer.setLastName(lastname);
                customer.setAge(customerDTO.getAge());
                customerRepository.save(customer);
                return ResponseEntity.status(HttpStatus.OK).body(customerDTO);
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Person name must contain two strings separated by a whitespace");
            }
        } else {
            String errorMessage = "Customer with id " + requestId + " not found";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
        }
    }

    public ResponseEntity deleteCustomerById(String id) {
        String message = "Customer with id " + id;
        Optional<Customer> customerOptional = customerRepository.findById(Long.valueOf(id));
        if(customerOptional.isPresent()) {
            //If record was found, then delete record
            customerRepository.delete(customerOptional.get());
            return ResponseEntity.status(HttpStatus.OK).body(message + " removed successfully");
        } else {
            //Return error message
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message + " not found");
        }
    }
}