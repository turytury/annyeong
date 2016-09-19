package com.tury.domain;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Product {
	private Integer id;
	private String name;
	private Double price;
	private Date createDate;
	private Category category;

	public Product() {}

	public Product(String name, Double price, Date createDate, Category category) {
		this.name = name;
		this.price = price;
		this.createDate = createDate;
		this.category = category;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public String getCreateDateDisplay() {
		return new SimpleDateFormat("yyyy-MM-dd HH:mm").format(createDate);
	}
}
