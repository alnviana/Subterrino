package com.subterrino.entity;

public class CartItem {
	
	private Integer id;
	private Product product;
	private Integer count;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Double getTotal() {
		return product.getPrice()*count;
	}
}
