package com.ups.oop.dto;


import lombok.*;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter

public class ReceiptDTO {
    private String id;
    private String serial;
    private String customer;
    private Date date;
    private Double totalprice;
    private String employee;
    private String paymentmethod;
}
