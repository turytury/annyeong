package com.tury.mappers;

import com.tury.domain.Product;
import com.tury.domain.ProductSearchForm;

import java.util.List;

public interface ProductMapper {

	int create(Product product);

	int update(Product product);

	int delete(Integer id);

	Product findById(Integer id);

	List<Product> findAll();

	List<Product> searchIds(ProductSearchForm productSearchForm);

	List<Product> searchWithLimit(ProductSearchForm productSearchForm);


}