package com.example.commerce.util;

public interface GrocerySearchUtil {

	String GROCERY_SEARCH ="  with results as ( "
			+ "  select * from commerce.groceries g "
			+ "  where g.grocery_search_vector @@ to_tsquery('english', :keyword || ':*') "
			+ "  union all "
			+ "  select * from commerce.groceries g "
			+ "  where g.brand_search_vector @@ to_tsquery('english', :keyword || ':*') "
			+ "   union all "
			+ "  select * from commerce.groceries g "
			+ "  where g.desc_search_vector @@ to_tsquery('english', :keyword || ':*') "
			+ "  ) select r.id grocery_id, r.name grocery_name, r.description, b.name brand, sc.name subcategory, c.name category, "
			+ "  s.size, s.price, i.quantity "
			+ "  from results r "
			+ "  left join commerce.skus s on r.id = s.grocery_id "
			+ "  left JOIN commerce.inventory i ON s.id = i.sku_id "
			+ "  left JOIN commerce.brands b ON b.id = s.brand_id "
			+ "  left JOIN commerce.stores s2 ON s2.id = i.store_id "
			+ "  left join commerce.subcategories sc on sc.id=r.subcategory_id "
			+ "  left join commerce.categories c on sc.category_id=c.id "
			+ "  WHERE i.store_id = :storeId"
			+ " limit :limit offset :offset";
	
	
}
