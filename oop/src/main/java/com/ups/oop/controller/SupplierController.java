package com.ups.oop.controller;

import com.ups.oop.dto.SupplierDTO;
import com.ups.oop.service.SupplierService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class SupplierController {
    private  final SupplierService supplierService;

    public SupplierController(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    @PostMapping("/supplier")
    public ResponseEntity createSupplier(@RequestBody SupplierDTO supplierDTO){
        return this.supplierService.createSupplier(supplierDTO);
    }

    @GetMapping("/get-all-supplier")
    public ResponseEntity getAllSupplier(){
        return this.supplierService.getAllSupplier();
    }

    @GetMapping("/get-supplier")
    public ResponseEntity getSupplierById(@RequestParam String id){
        return this.supplierService.getSupplierbyId(id);
    }

    @PutMapping("/update-supplier")
    public ResponseEntity updateSupplier(@RequestBody SupplierDTO supplierDTO){
        return this.supplierService.updateSupplier(supplierDTO);
    }

    @DeleteMapping("/remove-supplier")
    public ResponseEntity deleteSupplier(@RequestParam String id){
        return this.supplierService.deleteSupplierById(id);
    }
}
