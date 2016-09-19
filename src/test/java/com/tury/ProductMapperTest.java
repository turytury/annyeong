package com.tury;

import com.tury.domain.Category;
import com.tury.domain.Product;
import com.tury.mappers.ProductMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(Application.class)
public class ProductMapperTest {

	@Autowired
	private ProductMapper productMapper;
	
	@Test
	public void findAll() {
		List<Product> products = productMapper.findAll();
		assertNotNull(products);
		assertTrue(!products.isEmpty());
	}

	@Test
	public void findById() {
		Product product = productMapper.findById(1);
		assertNotNull(product);
	}
	
	@Test
	public void save() {
		Category category = new Category(8, "category", 2);
		Product product = new Product(0, "product", 1490.0, Date.valueOf("2016-09-09"), category);
		productMapper.create(product);
		Product newProduct = productMapper.findById(product.getId());
		assertEquals("product", newProduct.getName());
		assertEquals("1490.0", newProduct.getPrice().toString());
		assertEquals("8", newProduct.getCategory().getId().toString());
	}
}