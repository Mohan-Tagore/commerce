package com.example.commerce.service;

import com.example.commerce.dto.GroceryDTO;
import com.example.commerce.model.Groceries;
import com.example.commerce.repository.GroceryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroceryService {

    @Autowired
    private GroceryRepository productRepository;

    public Groceries saveProduct(Groceries product) {
        return productRepository.save(product);
    }

    public List<Groceries> getAllProducts() {
        return productRepository.findAll();
    }

    public List<GroceryDTO> searchProductsByStore(String keyword, Long storeId) {
        return productRepository.searchProductsByStore(keyword, storeId);
    }
    
    public List<GroceryDTO> searchGroceriesByStore(String keyword, Integer storeId, Integer pageNumber, Integer pageSize) {
    	int offset = (pageNumber>0?pageNumber-1:0)*pageSize;
		return productRepository.searchGroceriesByStore(keyword, storeId, pageSize, offset);
    }
    
    public List<Groceries> searchProducts(String keyword) {
        return productRepository.findByName(keyword);
    }
}
