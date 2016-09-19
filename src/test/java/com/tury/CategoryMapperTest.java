package com.tury;

import com.tury.domain.Category;
import com.tury.mappers.CategoryMapper;
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
public class CategoryMapperTest {

	@Autowired
	private CategoryMapper categoryMapper;

	@Test
	public void findAllCategorys() {
		List<Category> categories = categoryMapper.findAll();
		assertNotNull(categories);
		assertTrue(!categories.isEmpty());
	}

	@Test
	public void findCategoryById() {
		Category category = categoryMapper.findById(1);
		assertNotNull(category);
	}
}
