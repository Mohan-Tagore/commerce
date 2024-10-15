package com.example.commerce.model;



import java.util.Set;

import com.example.commerce.dto.GroceryDTO;
import com.example.commerce.util.GrocerySearchUtil;

import jakarta.persistence.Column;
import jakarta.persistence.ColumnResult;
import jakarta.persistence.ConstructorResult;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedNativeQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.SqlResultSetMapping;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(schema = "commerce", name = "groceries")
@SqlResultSetMapping(
	    name = "GroceryDTOMapping",
	    classes = @ConstructorResult(
	        targetClass = GroceryDTO.class,
	        columns = {
	            @ColumnResult(name = "name", type = String.class),
	            @ColumnResult(name = "size", type = String.class),
	            @ColumnResult(name = "price", type = Double.class),
	            @ColumnResult(name = "quantity", type = Integer.class),
	            @ColumnResult(name = "storeName", type = String.class),
	            @ColumnResult(name = "brand", type = String.class)
	        }
	    )
)
@NamedNativeQuery(
	    name = "Grocery.searchProductsByStore",
	    query = "SELECT p.name, s.size, s.price, i.quantity, s2.name AS storeName, b.name as brand " +
	            "FROM commerce.groceries p " +
	            "JOIN commerce.skus s ON p.id = s.grocery_id " +
	            "LEFT JOIN commerce.brands b ON b.id = s.brand_id " +
	            "JOIN commerce.inventory i ON s.id = i.sku_id " +
	            "JOIN commerce.stores s2 ON s2.id = i.store_id " +
	            "WHERE i.store_id = :storeId " +
	            "AND p.search_vector @@ to_tsquery('english', :keyword || ':*')",
	    resultSetMapping = "GroceryDTOMapping"
)
@SqlResultSetMapping(
	    name = "GroceryDTOMapping1",
	    classes = @ConstructorResult(
	        targetClass = GroceryDTO.class,
	        columns = {
	        	@ColumnResult(name = "grocery_id", type = Integer.class),
	            @ColumnResult(name = "grocery_name", type = String.class),
	            @ColumnResult(name = "description", type = String.class),
	            @ColumnResult(name = "subcategory", type = String.class),
	            @ColumnResult(name = "category", type = String.class),
	            @ColumnResult(name = "size", type = String.class),
	            @ColumnResult(name = "price", type = Double.class),
	            @ColumnResult(name = "quantity", type = Integer.class),
	            @ColumnResult(name = "brand", type = String.class)
	        }
	    )
)
@NamedNativeQuery(
		name = "Grocery.searchGroceriesByStore",
		query=GrocerySearchUtil.GROCERY_SEARCH,
		resultSetMapping = "GroceryDTOMapping1"
		)

public class Groceries {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;

    @ManyToOne
    @JoinColumn(name = "subcategory_id")
    private Subcategory subcategory;

    @OneToMany(mappedBy = "grocery")
    private Set<Sku> skus;

    @Column(columnDefinition = "tsvector", name="grocery_search_vector")
    private String searchVector;

    @PrePersist
    @PreUpdate
    public void updateSearchVector() {
        this.searchVector = String.join(" ", name, description, 
            subcategory.getName(), subcategory.getCategory().getName());
    }

    // Getters and Setters
}
