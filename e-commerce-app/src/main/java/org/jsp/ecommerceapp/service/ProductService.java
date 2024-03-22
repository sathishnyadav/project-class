package org.jsp.ecommerceapp.service;

import java.util.List;
import java.util.Optional;

import org.jsp.ecommerceapp.dao.MerchantDao;
import org.jsp.ecommerceapp.dao.ProductDao;
import org.jsp.ecommerceapp.dao.UserDao;
import org.jsp.ecommerceapp.dto.ResponseStructure;
import org.jsp.ecommerceapp.exception.MerchantNotFoundException;
import org.jsp.ecommerceapp.exception.ProductNotFoundException;
import org.jsp.ecommerceapp.model.Merchant;
import org.jsp.ecommerceapp.model.Product;
import org.jsp.ecommerceapp.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
	@Autowired
	private ProductDao productDao;
	@Autowired
	private MerchantDao merchantDao;
	@Autowired
	private UserDao userDao;

	public ResponseEntity<ResponseStructure<Product>> findById(int id) {
		ResponseStructure<Product> structure = new ResponseStructure<>();
		Optional<Product> recProduct = productDao.findById(id);
		if (recProduct.isEmpty()) {
			throw new ProductNotFoundException("Invalid Product Id");
		}
		structure.setBody(recProduct.get());
		structure.setMessage("Product Found");
		structure.setStatusCode(HttpStatus.OK.value());
		return new ResponseEntity<ResponseStructure<Product>>(structure, HttpStatus.OK);
	}

	public ResponseEntity<ResponseStructure<Product>> saveProduct(Product product, int merchant_id) {
		Optional<Merchant> recMechant = merchantDao.findById(merchant_id);
		ResponseStructure<Product> structure = new ResponseStructure<>();
		if (recMechant.isPresent()) {
			Merchant merchant = recMechant.get();
			merchant.getProducts().add(product);
			product.setMerchant(merchant);
			structure.setBody(productDao.saveProduct(product));
			structure.setMessage("Product Added");
			structure.setStatusCode(HttpStatus.CREATED.value());
			return new ResponseEntity<ResponseStructure<Product>>(structure, HttpStatus.CREATED);
		}
		throw new MerchantNotFoundException("cannot add Product as Merchant Id is Invalid");
	}

	public ResponseEntity<ResponseStructure<Product>> updateProduct(Product product) {
		ResponseStructure<Product> structure = new ResponseStructure<>();
		Optional<Product> recProduct = productDao.findById(product.getId());
		if (recProduct.isEmpty()) {
			throw new ProductNotFoundException("cannot update product as Id is Invalid");
		}
		Product dbProduct = recProduct.get();
		dbProduct.setBrand(product.getBrand());
		dbProduct.setCategory(product.getCategory());
		dbProduct.setDescription(product.getDescription());
		dbProduct.setCost(product.getCost());
		dbProduct.setImage_url(product.getImage_url());
		dbProduct.setName(product.getName());
		structure.setBody(productDao.saveProduct(dbProduct));
		structure.setMessage("Product Updated");
		structure.setStatusCode(HttpStatus.ACCEPTED.value());
		return new ResponseEntity<ResponseStructure<Product>>(structure, HttpStatus.ACCEPTED);

	}

	public ResponseEntity<ResponseStructure<List<Product>>> findProductsByMerchantId(int merchant_id) {
		ResponseStructure<List<Product>> structure = new ResponseStructure<>();
		List<Product> products = productDao.findByMerchantId(merchant_id);
		if (products.isEmpty()) {
			throw new ProductNotFoundException("No Products Found for entered Merchant Id");
		}
		structure.setBody(products);
		structure.setMessage("List of Products for Merchant Id");
		structure.setStatusCode(HttpStatus.OK.value());
		return new ResponseEntity<ResponseStructure<List<Product>>>(structure, HttpStatus.OK);
	}

	public ResponseEntity<ResponseStructure<List<Product>>> findByBrand(String brand) {
		ResponseStructure<List<Product>> structure = new ResponseStructure<>();
		List<Product> products = productDao.findByBrand(brand);
		if (products.isEmpty()) {
			throw new ProductNotFoundException("No Products Found for entered brand");
		}
		structure.setBody(products);
		structure.setMessage("List of Products for the entered brand");
		structure.setStatusCode(HttpStatus.OK.value());
		return new ResponseEntity<ResponseStructure<List<Product>>>(structure, HttpStatus.OK);
	}

	public ResponseEntity<ResponseStructure<List<Product>>> findByCategory(String category) {
		ResponseStructure<List<Product>> structure = new ResponseStructure<>();
		List<Product> products = productDao.findByCategory(category);
		if (products.isEmpty()) {
			throw new ProductNotFoundException("No Products Found for entered Category");
		}
		structure.setBody(products);
		structure.setMessage("List of Products for entered Category");
		structure.setStatusCode(HttpStatus.OK.value());
		return new ResponseEntity<ResponseStructure<List<Product>>>(structure, HttpStatus.OK);
	}

	public ResponseEntity<ResponseStructure<List<Product>>> findByName(String name) {
		ResponseStructure<List<Product>> structure = new ResponseStructure<>();
		List<Product> products = productDao.findByName(name);
		if (products.isEmpty()) {
			throw new ProductNotFoundException("No Products Found for entered name");
		}
		structure.setBody(products);
		structure.setMessage("List of Products for entered name");
		structure.setStatusCode(HttpStatus.OK.value());
		return new ResponseEntity<ResponseStructure<List<Product>>>(structure, HttpStatus.OK);
	}

	public ResponseEntity<ResponseStructure<List<Product>>> findAll() {
		ResponseStructure<List<Product>> structure = new ResponseStructure<>();
		List<Product> products = productDao.findAll();
		structure.setBody(products);
		structure.setMessage("List of Products for Merchant Id");
		structure.setStatusCode(HttpStatus.OK.value());
		return new ResponseEntity<ResponseStructure<List<Product>>>(structure, HttpStatus.OK);
	}

	public ResponseEntity<ResponseStructure<String>> deleteById(int id) {
		ResponseStructure<String> structure = new ResponseStructure<>();
		Optional<Product> recProduct = productDao.findById(id);
		if (recProduct.isEmpty())
			throw new ProductNotFoundException("Invalid Prodcut Id");
		productDao.delete(id);
		structure.setBody("Product Found");
		structure.setMessage("Product ddeleted");
		structure.setStatusCode(HttpStatus.OK.value());
		return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.OK);
	}

	public ResponseEntity<ResponseStructure<String>> addToCart(int user_id, int product_id) {
		Optional<Product> recProduct = productDao.findById(product_id);
		Optional<User> recUser = userDao.findById(user_id);
		if (recProduct.isEmpty() || recUser.isEmpty())
			throw new IllegalArgumentException("Invalid User Id or Product Id");

		recUser.get().getCart().add(recProduct.get());
		userDao.saveUser(recUser.get());
		ResponseStructure<String> structure = new ResponseStructure<>();
		structure.setBody("User and Product Found");
		structure.setMessage("Added product to cart");
		structure.setStatusCode(HttpStatus.ACCEPTED.value());
		return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.ACCEPTED);

	}

	public ResponseEntity<ResponseStructure<String>> addToWishList(int user_id, int product_id) {
		Optional<Product> recProduct = productDao.findById(product_id);
		Optional<User> recUser = userDao.findById(user_id);
		if (recProduct.isEmpty() || recUser.isEmpty())
			throw new IllegalArgumentException("Invalid User Id or Product Id");

		recUser.get().getWishList().add(recProduct.get());
		userDao.saveUser(recUser.get());
		ResponseStructure<String> structure = new ResponseStructure<>();
		structure.setBody("User and Product Found");
		structure.setMessage("Added product to WishList");
		structure.setStatusCode(HttpStatus.ACCEPTED.value());
		return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.ACCEPTED);

	}
}
