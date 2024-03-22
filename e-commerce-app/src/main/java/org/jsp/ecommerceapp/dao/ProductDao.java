package org.jsp.ecommerceapp.dao;

import java.util.List;
import java.util.Optional;

import org.jsp.ecommerceapp.model.Product;
import org.jsp.ecommerceapp.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ProductDao {
	@Autowired
	private ProductRepository productRepository;

	public Product saveProduct(Product product) {
		return productRepository.save(product);
	}

	public Optional<Product> findById(int id) {
		return productRepository.findById(id);
	}

	public List<Product> findAll() {
		return productRepository.findAll();
	}

	public List<Product> findByBrand(String brand) {
		return productRepository.findByBrand(brand);
	}

	public List<Product> findByMerchantId(int id) {
		return productRepository.findByMerchantId(id);
	}

	public List<Product> findByCategory(String category) {
		return productRepository.findByCategory(category);
	}

	public List<Product> findByName(String name) {
		return productRepository.findByCategory(name);
	}

	public void delete(int id) {
		productRepository.deleteById(id);
	}
}
