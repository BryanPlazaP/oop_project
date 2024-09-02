package com.ups.oop.service;

import com.ups.oop.dto.CustomerDTO;
import com.ups.oop.dto.EmployeeDTO;
import com.ups.oop.entity.Customer;
import com.ups.oop.entity.Employee;
import com.ups.oop.repository.EmployeeRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private List<EmployeeDTO> employeeDTOList = new ArrayList<>();

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public ResponseEntity createEmployee(EmployeeDTO employeeDTO) {
        String employeeId = employeeDTO.getId();
        //check repository if record exist
        Optional<Employee> employeeOptional = employeeRepository.findById(Long.valueOf(employeeId));
        if(employeeOptional.isPresent()) {
            String errorMessage = "Employee with id " + employeeId + " already exists";
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
        } else {
            //Before Register Person, name and lastname are present
            if(employeeDTO.getName().contains(" ")) {
                //Build Person and save in Repository
                Employee employeeRecord = new Employee();
                employeeRecord.setEmployeeCode(employeeId);
                String[] nameStrings = employeeDTO.getName().split(" ");
                String name = nameStrings[0];
                String lastname = nameStrings[1];
                employeeRecord.setName(name);
                employeeRecord.setLastName(lastname);
                employeeRecord.setAge(employeeDTO.getAge());
                employeeRepository.save(employeeRecord);
                return ResponseEntity.status(HttpStatus.OK).body(employeeDTO);
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Employee name must contain two strings separated by a whitespace");
            }
        }
    }

    public ResponseEntity getAllEmployee() {
        List<EmployeeDTO> employeeList = getEmployee();
        if(employeeList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Employee List not found");
        }
        return ResponseEntity.status(HttpStatus.OK).body(employeeList);
    }

    public List<EmployeeDTO> getEmployee() {
        Iterable<Employee> personIterable = employeeRepository.findAll();
        List<EmployeeDTO> customerList = new ArrayList<>();
        for(Employee emp : personIterable) {
            EmployeeDTO customer = new EmployeeDTO();
            customer.setId(emp.getPersonId());
            customer.setName(emp.getName() + "-" + emp.getLastName());
            customer.setAge(emp.getAge());
            customerList.add(customer);
        }
        return customerList;
    }

    public ResponseEntity getEmployeebyId(String employeeId) {
        Optional<Employee> employeeOptional = employeeRepository.findById(Long.valueOf(employeeId));
        if(employeeOptional.isPresent()) {
            //if record was found
            Employee employeeFound = employeeOptional.get();
            EmployeeDTO customer = new EmployeeDTO(employeeFound.getPersonId(),
                    employeeFound.getName() + "-" + employeeFound.getLastName(),
                    employeeFound.getAge(), employeeFound.getEmployeeCode());
            return ResponseEntity.status(HttpStatus.OK).body(customer);
        } else {
            //if record wasn't found
            String errorMessage = "Employee with id " + employeeId + " not found";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
        }
    }

    public ResponseEntity updateEmployee(EmployeeDTO employeeDTO) {
        String employeeId = employeeDTO.getId();
        //check repository if record exist
        Optional<Employee> employeeOptional = employeeRepository.findById(Long.valueOf(employeeId));
        if(employeeOptional.isPresent()) {
            //If record exists, then perform Update
            Employee employee = employeeOptional.get();
            if(employeeDTO.getName().contains(" ")) {
                //Build Person and save in Repository
                employee.setPersonId(employeeId);
                String[] nameStrings = employeeDTO.getName().split(" ");
                String name = nameStrings[0];
                String lastname = nameStrings[1];
                employee.setName(name);
                employee.setLastName(lastname);
                employee.setAge(employeeDTO.getAge());
                employeeRepository.save(employee);
                return ResponseEntity.status(HttpStatus.OK).body(employeeDTO);
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Employee name must contain two strings separated by a whitespace");
            }
        } else {
            String errorMessage = "Employee with id " + employeeId + " not found";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
        }
    }

    public ResponseEntity deleteCustomerById(String id) {
        String message = "Employee with id " + id;
        Optional<Employee> employeeOptional = employeeRepository.findById(Long.valueOf(id));
        if(employeeOptional.isPresent()) {
            //If record was found, then delete record
            employeeRepository.delete(employeeOptional.get());
            return ResponseEntity.status(HttpStatus.OK).body(message + " removed successfully");
        } else {
            //Return error message
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message + " not found");
        }
    }
}