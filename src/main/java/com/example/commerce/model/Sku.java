package com.example.commerce.model;

import java.math.BigDecimal;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(schema = "commerce",name="skus")
public class Sku {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String size;
    private BigDecimal price;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "grocery_id")
    private Groceries grocery;
    
    @OneToOne
    @JoinColumn(name = "brand_id")
    private Brands brand;

    @OneToMany(mappedBy = "sku")
    private Set<Inventory> inventories;

    // Getters and Setters
}
