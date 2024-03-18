package org.jsp.ecommerceapp.repository;

import java.util.List;

import org.jsp.ecommerceapp.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductRepository extends JpaRepository<Product, Integer> {
	List<Product> findByBrand(String brand);

	List<Product> findByCategory(String category);

	@Query("select p from Product p where p.merchant.id=?1")
	List<Product> findByMerchantId(int merchant_id);

	List<Product> findByName(String name);
}
