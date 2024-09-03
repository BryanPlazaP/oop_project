package com.ups.oop.controller;

import com.ups.oop.dto.StoreDTO;
import com.ups.oop.service.StoreService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class StoreController {
    private  final StoreService storeService;

    public StoreController(StoreService storeService) {
        this.storeService = storeService;
    }

    @PostMapping("/store")
    public ResponseEntity createCustomer(@RequestBody StoreDTO storeDTO){
        return this.storeService.createStore(storeDTO);
    }

    @GetMapping("/get-all-store")
    public ResponseEntity getAllCustomer(){
        return this.storeService.getAllStore();
    }

    @GetMapping("/get-store")
    public ResponseEntity getCustomerById(@RequestParam String id){
        return this.storeService.getStorebyId(id);
    }

    @PutMapping("/update-store")
    public ResponseEntity updateCustomer(@RequestBody StoreDTO storeDTO){
        return this.storeService.updateStore(storeDTO);
    }

    @DeleteMapping("/remove-store")
    public ResponseEntity deleteCustomer(@RequestParam String id){
        return this.storeService.deleteStoreById(id);
    }
}
