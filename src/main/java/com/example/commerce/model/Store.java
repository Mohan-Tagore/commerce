package com.example.commerce.model;

import jakarta.persistence.*;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Getter
@Setter
@Table(schema = "commerce",name="stores")
public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String location;

    @JsonBackReference
    @OneToMany(mappedBy = "store")
    private Set<Inventory> inventories;

    // Getters and Setters
}
