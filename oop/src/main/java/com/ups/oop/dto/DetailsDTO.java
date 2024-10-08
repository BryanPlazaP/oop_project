package com.ups.oop.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter

public class DetailsDTO {
    private String id;
    private String receipt;
    private String product;
    private Double unitprice;
    private int quantity;
}
