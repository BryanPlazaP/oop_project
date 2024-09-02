package com.ups.oop.service;

import com.ups.oop.dto.ReceiptDTO;
import com.ups.oop.entity.Receipt;
import com.ups.oop.repository.ReceiptRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReceiptService {

    private final ReceiptRepository receiptRepository;

    public ReceiptService(ReceiptRepository receiptRepository) {
        this.receiptRepository = receiptRepository;
    }

    public List<ReceiptDTO> getReceipts() {
        Iterable<Receipt> receiptIterable = receiptRepository.findAll();
        List<ReceiptDTO> receiptList = new ArrayList<>();

        for (Receipt receipt : receiptIterable) {
            ReceiptDTO receiptDTO = new ReceiptDTO();
            receiptDTO.setId(receipt.getId().toString());
            receiptDTO.setSerial(receipt.getSerial());
            receiptDTO.setCustomer(receipt.getCustomer().getName() + " " +receipt.getCustomer().getLastName());
            receiptDTO.setDate(receipt.getReceiptDate());
            receiptDTO.setTotalprice(receipt.getTotal_price().doubleValue());
            receiptDTO.setEmployee(receipt.getEmployee().getName() + " " + receipt.getEmployee().getLastName());
            receiptDTO.setPaymentmethod(receipt.getPaymentMeth().getMethod());

            receiptList.add(receiptDTO);
        }

        return receiptList;
    }
}
