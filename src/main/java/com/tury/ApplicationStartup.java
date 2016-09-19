package com.tury;

import com.tury.domain.Category;
import com.tury.domain.Product;
import com.tury.mappers.CategoryMapper;
import com.tury.mappers.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.util.Calendar;

@Component
public class ApplicationStartup implements ApplicationListener<ContextRefreshedEvent>{

    private static final int categoryAmountLevelOne = 5;
    private static final int categoryAmountLevelTwo = 7;
    private static final int productAmount = 100;

    private static final String categoryNameLevelOne = "CF";
    private static final String categoryNameLevelTwo = "CS";
    private static final String productName = "P";

    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private ProductMapper productMapper;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        if(categoryMapper.findAll().isEmpty()) {
            long startTime = new java.util.Date().getTime();

            // Create 1st level category
            for (int i = 1; i <= categoryAmountLevelOne; i++) {
                categoryMapper.create(new Category(categoryNameLevelOne + i, 0));
                Category parentCategory = categoryMapper.findLastRow();

                // Create 2nd level category
                for (int j = 1; j <= categoryAmountLevelTwo; j++) {
                    categoryMapper.create(new Category(categoryNameLevelTwo + j, parentCategory.getId()));
                    Category category = categoryMapper.findLastRow();

                    // Create product per category
                    Calendar calendar = Calendar.getInstance();
                    Double productPrice = 500.00;
                    String prefix = parentCategory.getName() + "_" + category.getName();
                    for (int k = 1; k <= productAmount; k++) {
                        calendar.add(Calendar.MINUTE, -k * 10);
                        productMapper.create(
                                new Product(
                                        prefix + "_" + productName + k,
                                        productPrice + (k * 150),
                                        new Date(calendar.getTimeInMillis()),
                                        category));
                    }
                }
            }
            long finishTime = new java.util.Date().getTime();
            System.out.println("Executed ApplicationStartup in " + (finishTime - startTime) + " ms.");
        }
    }
}
