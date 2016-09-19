package com.tury.domain;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ProductDisplay {
	private Integer id;
	private String name;
	private String price;
	private Date createDate;
	private String categoryParent;
	private String category;

	public ProductDisplay(Integer id, String name, String price, Date createDate, String categoryParent, String category) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.createDate = createDate;
		this.categoryParent = categoryParent;
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

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getCategoryParent() {
		return categoryParent;
	}

	public void setCategoryParent(String categoryParent) {
		this.categoryParent = categoryParent;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getCreateDateDisplay() {
		return new SimpleDateFormat("yyyy-MM-dd HH:mm").format(createDate);
	}
}
