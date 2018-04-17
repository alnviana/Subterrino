package com.subterrino.controller;

import java.io.IOException;

import javax.faces.bean.ManagedBean;

import org.apache.catalina.core.ApplicationPart;

import com.subterrino.entity.Color;
import com.subterrino.entity.ProductF;

@ManagedBean(name = "mBeanProductFacade")
public class MBeanProductFacade {
	private Integer id;
	private String name;
	private String description;
	private Double price;	
	private String colorname;
	
	private ApplicationPart[] photoList = new ApplicationPart[9];

	public String save() throws IOException {
		try {
			ProductF product = new ProductF();
			product.setId(id);
			product.setName(name);
			product.setDescription(description);
			product.setPrice(price);		
			product.setPhotoList(photoList);
			
			Color color = new Color();
			color.setId(null);
			color.setName(colorname);
			product.setColor(color);
			
			try {
				new RestClient<ProductF>().request("http://localhost:8080/Subterrino/rest/ProductFacade", "POST", product, ProductF.class);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}	
		
		return "product_manager.jsf";
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

	public String getColorname() {
		return colorname;
	}

	public void setColorname(String colorname) {
		this.colorname = colorname;
	}

	public ApplicationPart[] getPhotoList() {
		return photoList;
	}

	public void setPhoto(ApplicationPart[] photoList) {
		this.photoList = photoList;
	}
}
