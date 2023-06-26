package com.hka.vslab.product.repositories;

import com.hka.vslab.product.model.Category;
import com.hka.vslab.product.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    Product findByName(String name);

    void deleteByName(String name);

    List<Product> findProductsByDetailsLikeAndPriceGreaterThanEqualAndPriceLessThanEqual(
            String search, Double minPrice, Double maxPrice);

    int deleteAllByCategoryId(Category cat);

    List<Product> getProductsByCategoryId(int categoryId);
}
