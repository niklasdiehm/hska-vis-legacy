package com.hka.vslab.product.controller;

import com.hka.vslab.product.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.hka.vslab.product.model.Product;
import com.hka.vslab.product.model.Category;
import com.hka.vslab.product.services.CategoryService;

import java.util.List;

class NewProductData {
	private String name;
	private Double price;
	private String details;
	private int categoryId;

	public NewProductData() {
	}

	public NewProductData(String name, Double price, int categoryId) {
		this.name = name;
		this.price = price;
		this.details = null;
		this.categoryId = categoryId;
	}

	public NewProductData(String name, Double price, String details, int categoryId) {
		this.name = name;
		this.price = price;
		this.details = details;
		this.categoryId = categoryId;
	}

	public String getName() {
		return name;
	}

	public Double getPrice() {
		return price;
	}

	public String getDetails() {
		return details;
	}

	public int getCategoryId() {
		return categoryId;
	}
}

@RestController
public class ProductController {

	@Autowired
	private CategoryService categoryService;
	@Autowired
	private ProductService productService;

	@GetMapping("/hello")
	public String getHello() {
		return "world";
	}

	@GetMapping("/products")
	public List<Product> getProducts() {
		return productService.getAll();
	}

	@GetMapping("/products/{id}")
	public Product getProductById(@PathVariable("id") int id) {
		return productService.getProduct(id);
	}

	@GetMapping("/products/{name}")
	public Product getProductByName(@PathVariable("name") String name) {
		return productService.getProductByName(name);
	}

	@PostMapping("/products")
	public int addProduct(@RequestBody NewProductData newProduct) {
		int productId = -1;
		Category category = categoryService.getCategory(newProduct.getCategoryId());
		Product product = new Product(newProduct.getName(), newProduct.getPrice(), category, newProduct.getDetails());

		if(category != null){
			productService.addProduct(product);
			productId = product.getId();
		}

		return productId;
	}

	@GetMapping("/products/search")
	public List<Product> getProductsForSearchValues(
			@RequestParam("searchString") String searchValue,
			@RequestParam("minPrice") Double searchMinPrice,
			@RequestParam("maxPrice") Double searchMaxPrice
	) {
		return productService.findProductsBySearch(searchValue, searchMinPrice, searchMaxPrice);
	}

	@DeleteMapping("/products/{id}")
	public boolean deleteProductsByCategoryId(@PathVariable("id") int categoryId) {
		Category category = categoryService.getCategory(categoryId);
		return productService.deleteAllByCategory(category) > 0;
	}

	@DeleteMapping("/product/{id}")
	public void deleteProductById(@PathVariable("id") int id) {
		productService.delProduct(id);
	}
}
