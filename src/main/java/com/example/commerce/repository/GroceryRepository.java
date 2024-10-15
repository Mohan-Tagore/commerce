package com.example.commerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.commerce.dto.GroceryDTO;
import com.example.commerce.model.Groceries;

@Repository
public interface GroceryRepository extends JpaRepository<Groceries, Long> {

//    @Query(value = "SELECT p.* FROM commerce.groceries p JOIN commerce.skus s ON p.id = s.grocery_id JOIN commerce.brands b on b.id=s.brand_id JOIN commerce.inventory i ON s.id = i.sku_id WHERE i.store_id = :storeId AND p.search_vector @@ plainto_tsquery('english', :keyword)", nativeQuery = true)
//    List<Groceries> searchProductsByStore(@Param("keyword") String keyword, @Param("storeId") Long storeId);

	List<Groceries> findByName(String name);

	 @Query(name = "Grocery.searchProductsByStore", nativeQuery = true)
	 List<GroceryDTO> searchProductsByStore(@Param("keyword") String keyword, @Param("storeId") Long storeId);
	 
	 @Query(name = "Grocery.searchGroceriesByStore", nativeQuery = true)
	 List<GroceryDTO> searchGroceriesByStore(@Param("keyword") String keyword, @Param("storeId") Integer storeId, @Param(value="limit") Integer limit, @Param(value="offset") Integer offset );
}
