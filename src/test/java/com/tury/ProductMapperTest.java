package com.tury;

import com.tury.domain.Product;
import com.tury.mappers.ProductMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

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
}