package com.tury.mappers;

import com.tury.domain.Category;

import java.util.List;

public interface CategoryMapper {

	int create(Category category);

	Category findById(Integer id);

	Category findLastRow();

	List<Category> findAll();

	List<Category> findByParentId(Integer parentId);
}
