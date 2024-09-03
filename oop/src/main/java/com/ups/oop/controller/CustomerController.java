package com.ups.oop.controller;

import com.ups.oop.dto.CustomerDTO;
import com.ups.oop.service.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CustomerController {
    private  final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/customer")
    public ResponseEntity createCustomer(@RequestBody CustomerDTO customerDTO){
        return this.customerService.createCustomer(customerDTO);
    }

    @GetMapping("/get-all-customer")
    public ResponseEntity getAllCustomer(){
        return this.customerService.getAllCustomer();
    }

    @GetMapping("/get-customer")
    public ResponseEntity getCustomerById(@RequestParam String id){
        return this.customerService.getCustomerById(id);
    }

    @PutMapping("/update-customer")
    public ResponseEntity updateCustomer(@RequestBody CustomerDTO customerDTO){
        return this.customerService.updateCustomer(customerDTO);
    }

    @DeleteMapping("/remove-customer")
    public ResponseEntity deleteCustomer(@RequestParam String id){
        return this.customerService.deleteCustomerById(id);
    }
}
