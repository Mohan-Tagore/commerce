package com.example.commerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.commerce.dto.GroceryDTO;
import com.example.commerce.model.Groceries;
import com.example.commerce.service.GroceryService;

@RestController
@RequestMapping("/api/products")
public class GroceryController {

    @Autowired
    private GroceryService groceryService;

    @PostMapping
    public Groceries createProduct(@RequestBody Groceries product) {
        return groceryService.saveProduct(product);
    }

    @GetMapping
    public List<Groceries> getAllProducts() {
        return groceryService.getAllProducts();
    }

    @GetMapping("/search")
    public List<GroceryDTO> searchProductsByStore(@RequestParam String keyword, @RequestParam Long storeId) {
        return groceryService.searchProductsByStore(keyword, storeId);
    }
    
    @GetMapping("/search/product")
    public List<Groceries> searchProducts(@RequestParam String name) {
        return groceryService.searchProducts(name);
    }
    
    @GetMapping("/search/grocery")
    public List<GroceryDTO> searchGroceriesByStore(@RequestParam String keyword,
    		@RequestParam Integer storeId, @RequestParam(defaultValue = "1") Integer pageNumber,
    		@RequestParam(defaultValue = "10") Integer pageSize) {
        return groceryService.searchGroceriesByStore(keyword, storeId, pageNumber, pageSize);
    }
    
    @GetMapping("/search/suggestions")
    public List<Groceries> searchSuggestions(@RequestParam String keyword) {
        return groceryService.searchProducts(keyword);
    }
}
