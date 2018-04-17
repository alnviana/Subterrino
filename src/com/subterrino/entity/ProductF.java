package com.subterrino.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.apache.catalina.core.ApplicationPart;

@Entity
public class ProductF {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	private String name;
	private String description;
	private Double price;
	
	ApplicationPart[] photoList = new ApplicationPart[9];
	
	@ManyToOne
	@JoinColumn(name="FK_PRODUCT_COLOR")
	private Color color;

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public ApplicationPart[] getPhotoList() {
		return photoList;
	}

	public void setPhotoList(ApplicationPart[] photoList) {
		this.photoList = photoList;
	}
}
