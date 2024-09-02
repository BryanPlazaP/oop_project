package com.ups.oop.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter

public class ProductDTO {
    private String product_name;
    private String productId;
    private String Id;
    private double price;
    private List<String> supplier;
}