package com.ups.oop.controller;

import com.ups.oop.dto.EmployeeDTO;
import com.ups.oop.service.EmployeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class EmployeeController {
    private  final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping("/employee")
    public ResponseEntity createEmployee(@RequestBody EmployeeDTO employeeDTO){
        return this.employeeService.createEmployee(employeeDTO);
    }

    @GetMapping("/get-all-employee")
    public ResponseEntity getAllEmployee(){
        return this.employeeService.getAllEmployee();
    }

    @GetMapping("/get-employee")
    public ResponseEntity getEmployeeById(@RequestParam String id){
        return this.employeeService.getEmployeebyId(id);
    }

    @PutMapping("/update-employee")
    public ResponseEntity updateEmployee(@RequestBody EmployeeDTO employeeDTO){
        return this.employeeService.updateEmployee(employeeDTO);
    }

    @DeleteMapping("/remove-employee")
    public ResponseEntity deleteEmployee(@RequestParam String id){
        return this.employeeService.deleteEmployeeById(id);
    }
}
