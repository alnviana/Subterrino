package com.subterrino.entity;

public class CartItem {
	
	private Product product;
	private Integer count;
	
	public CartItem(Product product) {
		setProduct(product);
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
