package com.ups.oop.service;

import com.ups.oop.dto.StoreDTO;
import com.ups.oop.entity.Store;
import com.ups.oop.repository.ProductRepository;
import com.ups.oop.repository.StoreRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class StoreService {
    private final StoreRepository storeRepository;
    private List<StoreDTO> storeDTOList = new ArrayList<>();

    public StoreService(StoreRepository storeRepository, List<StoreDTO> storeDTOList) {
        this.storeRepository = storeRepository;
        this.storeDTOList = storeDTOList;
    }

    public ResponseEntity createStore(StoreDTO storeDTO) {
        String storeId = storeDTO.getId();
        //check repository if record exist
        Optional<Store> storeOptional = storeRepository.findById(Long.valueOf(storeId));
        if(storeOptional.isPresent()) {
            String errorMessage = "Store with id " + storeId + " already exists";
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
        } else {
            //Before Register Person, name and lastname are present
            if(storeDTO.getStore().contains(" ")) {
                //Build Person and save in Repository
                Store storeRecord = new Store();
                storeRecord.setId(storeRecord.getId());
                String[] nameStrings = storeDTO.getStore().split(" ");
                String[] nameStrings2 = storeDTO.getBranch().split(" ");
                String name = nameStrings[0];
                storeRecord.setBranch_name(name);
                storeRepository.save(storeRecord);
                return ResponseEntity.status(HttpStatus.OK).body(storeDTO);
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Product name must contain two strings separated by a whitespace");
            }
        }
    }

    public ResponseEntity getAllStore() {
        List<StoreDTO> storeList = getStore();
        if(storeList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Store List not found");
        }
        return ResponseEntity.status(HttpStatus.OK).body(storeList);
    }

    public List<StoreDTO> getStore() {
        Iterable<Store> storeIterable = storeRepository.findAll();
        List<StoreDTO> storeList = new ArrayList<>();
        for(Store sto : storeIterable) {
            StoreDTO store = new StoreDTO();
            store.setId(String.valueOf(sto.getId()));
            store.setStore(toString());
            store.setBranch(String.valueOf(sto.getBranch_name()));

            storeList.add(store);
        }
        return storeList;
    }

    public ResponseEntity getStorebyId(String storeId) {
        Optional<Store> storeOptional = storeRepository.findById(Long.valueOf(storeId));
        if(storeOptional.isPresent()) {
            //if record was found
            Store storeFound = storeOptional.get();
            StoreDTO Store = new StoreDTO(String.valueOf(storeFound.getId()), storeFound.getBranch_name(), storeFound.getStore_name());
            return ResponseEntity.status(HttpStatus.OK).body(Store);
        } else {
            //if record wasn't found
            String errorMessage = "Store with id " + storeId + " not found";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
        }
    }

    public ResponseEntity updateStore(StoreDTO storeDTO) {
        String storeId = storeDTO.getId();
        //check repository if record exist
        Optional<Store> storeOptional = storeRepository.findById(Long.valueOf(storeId));
        if(storeOptional.isPresent()) {
            //If record exists, then perform Update
            Store store = storeOptional.get();
            if(storeDTO.getStore().contains(" ")) {
                //Build Person and save in Repository
                store.setId(Long.valueOf(storeId));
                String[] nameStrings = storeDTO.getStore().split(" ");
                String name = nameStrings[0];
                store.setBranch_name(name);
                storeRepository.save(store);
                return ResponseEntity.status(HttpStatus.OK).body(storeDTO);
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Store name must contain two strings separated by a whitespace");
            }
        } else {
            String errorMessage = "Store with id " + storeId + " not found";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
        }
    }

    public ResponseEntity deleteStoreById(String id) {
        String message = "Product with id " + id;
        Optional<Store> productOptional = storeRepository.findById(Long.valueOf(id));
        if(productOptional.isPresent()) {
            //If record was found, then delete record
            storeRepository.delete(productOptional.get());
            return ResponseEntity.status(HttpStatus.OK).body(message + " removed successfully");
        } else {
            //Return error message
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message + " not found");
        }
    }
}