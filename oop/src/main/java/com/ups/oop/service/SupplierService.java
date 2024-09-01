package com.ups.oop.service;

import com.ups.oop.dto.SupplierDTO;
import com.ups.oop.entity.Store;
import com.ups.oop.entity.Supplier;
import com.ups.oop.repository.ProductRepository;
import com.ups.oop.repository.StoreRepository;
import com.ups.oop.repository.SupplierRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class SupplierService {
    private final SupplierRepository supplierRepository;
    private List<SupplierDTO> supplierDTOList = new ArrayList<>();

    public SupplierService(SupplierRepository supplierRepository, List<SupplierDTO> supplierDTOList) {
        this.supplierRepository = supplierRepository;
        this.supplierDTOList = supplierDTOList;
    }

    public ResponseEntity createSupplier(SupplierDTO supplierDTO) {
        String supplierId = supplierDTO.getId();
        //check repository if record exist
        Optional<Supplier> supplierOptional = supplierRepository.findBySupplierId(supplierId);
        if(supplierOptional.isPresent()) {
            String errorMessage = "Supplier with id " + supplierId + " already exists";
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
        } else {
            //Before Register Person, name and lastname are present
            if(supplierDTO.getName().contains(" ")) {
                //Build Person and save in Repository
                Supplier supplierRecord = new Supplier();
                supplierRecord.setId(supplierRecord.getId());
                String[] nameStrings = supplierDTO.getName().split(" ");
                String name = nameStrings[0];
                supplierRecord.setName(name);
                supplierRepository.save(supplierRecord);
                return ResponseEntity.status(HttpStatus.OK).body(supplierDTO);
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Product name must contain two strings separated by a whitespace");
            }
        }
    }

    public ResponseEntity getAllSupplier() {
        List<SupplierDTO> supplierList = getSupplier();
        if(supplierList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Supplier List not found");
        }
        return ResponseEntity.status(HttpStatus.OK).body(supplierList);
    }

    public List<SupplierDTO> getSupplier() {
        Iterable<Supplier> supplierIterable = supplierRepository.findAll();
        List<SupplierDTO> supplierList = new ArrayList<>();
        for(Supplier supp : supplierIterable) {
            SupplierDTO supplier = new SupplierDTO();
            supplier.setId(String.valueOf(supp.getId()));
            supplier.setName(supp.getName());
            supplierList.add(supplier);
        }
        return supplierList;
    }

    public ResponseEntity getSupplierbyId(String supplierId) {
        Optional<Supplier> supplierOptional = supplierRepository.findBySupplierId(supplierId);
        if(supplierOptional.isPresent()) {
            //if record was found
            Supplier supplierFound = supplierOptional.get();
            SupplierDTO Supplier = new SupplierDTO(String.valueOf(supplierFound.getId()), supplierFound.getName());
            return ResponseEntity.status(HttpStatus.OK).body(Supplier);
        } else {
            //if record wasn't found
            String errorMessage = "Supplier with id " + supplierId + " not found";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
        }
    }

    public ResponseEntity updateSupplier(SupplierDTO supplierDTO) {
        String requestId = supplierDTO.getId();
        //check repository if record exist
        Optional<Supplier> supplierOptional = supplierRepository.findBySupplierId(requestId);
        if(supplierOptional.isPresent()) {
            //If record exists, then perform Update
            Supplier supplier = supplierOptional.get();
            if(supplierDTO.getId().contains(" ")) {
                //Build Person and save in Repository
                supplier.setId(Long.valueOf(requestId));
                String[] nameStrings = supplierDTO.getName().split(" ");
                String name = nameStrings[0];
                supplier.setName(name);
                supplierRepository.save(supplier);
                return ResponseEntity.status(HttpStatus.OK).body(supplierDTO);
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Store name must contain two strings separated by a whitespace");
            }
        } else {
            String errorMessage = "Store with id " + requestId + " not found";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
        }
    }

    public ResponseEntity deleteSupplierById(String id) {
        String message = "Supplier with id " + id;
        Optional<Supplier> supplierOptional = supplierRepository.findBySupplierId(id);
        if(supplierOptional.isPresent()) {
            //If record was found, then delete record
            supplierRepository.delete(supplierOptional.get());
            return ResponseEntity.status(HttpStatus.OK).body(message + " removed successfully");
        } else {
            //Return error message
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message + " not found");
        }
    }
}