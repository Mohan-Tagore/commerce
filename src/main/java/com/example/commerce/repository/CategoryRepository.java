package com.example.commerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.commerce.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
