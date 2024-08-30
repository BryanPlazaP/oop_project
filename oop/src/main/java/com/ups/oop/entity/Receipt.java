package com.ups.oop.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Receipt {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String serial;
    @ManyToOne
    @JoinColumn(name = "client_id", nullable = true)
    private Customer customer;
    private Date receiptDate;
    private Integer total_price;
    @ManyToOne
    @JoinColumn(name= "employee_id", nullable = true)
    private Employee employee;
    @OneToMany(mappedBy = "receipt")
    private List<Details> detailList = new ArrayList<>();
}