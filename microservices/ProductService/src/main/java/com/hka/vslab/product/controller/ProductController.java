package com.hka.vslab.product.controller;

import com.hka.vslab.product.managers.ProductManager;
import com.hka.vslab.product.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.hka.vslab.product.model.Product;
import com.hka.vslab.product.model.Category;
import com.hka.vslab.product.services.CategoryService;

import java.util.List;

@RestController
public class ProductController implements ProductManager {

	@Autowired
	private CategoryService categoryService;
	@Autowired
	private ProductService productService;

	@GetMapping("/hello")
	public String getHello() {
		return "world";
	}

	@Override
	@GetMapping("/products")
	public List<Product> getProducts() {
		return productService.getAll();
	}

	@Override
	@GetMapping("/products/{id}")
	public Product getProductById(@PathVariable("id") int id) {
		return productService.getProduct(id);
	}

	@Override
	@GetMapping("/products/{name}")
	public Product getProductByName(@PathVariable("name") String name) {
		return productService.getProductByName(name);
	}

	@Override
	@PostMapping("/products")
	public int addProduct(
			@RequestBody String name,
			@RequestBody double price,
			@RequestBody int categoryId,
			@RequestBody String details
	) {
		int productId = -1;
		Category category = categoryService.getCategory(categoryId);

		if(category != null){
			Product product;
			if(details == null){
				product = new Product(name, price, category);
			} else{
				product = new Product(name, price, category, details);
			}

			productService.addProduct(product);
			productId = product.getId();
		}

		return productId;
	}

	@Override
	@GetMapping("/products/search")
	public List<Product> getProductsForSearchValues(
			@RequestParam("searchString") String searchValue,
			@RequestParam("minPrice") Double searchMinPrice,
			@RequestParam("maxPrice") Double searchMaxPrice
	) {
		return productService.findProductsBySearch(searchValue, searchMinPrice, searchMaxPrice);
	}

	@Override
	@DeleteMapping("/products/{id}")
	public boolean deleteProductsByCategoryId(@PathVariable("id") int categoryId) {
		Category category = categoryService.getCategory(categoryId);
		return productService.deleteAllByCategory(category) > 0;
	}

	@Override
	@DeleteMapping("/product/{id}")
	public void deleteProductById(@PathVariable("id") int id) {
		productService.delProduct(id);
	}
}
