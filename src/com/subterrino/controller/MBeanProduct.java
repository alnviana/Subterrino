package com.subterrino.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.apache.catalina.core.ApplicationPart;

import com.subterrino.entity.Color;
import com.subterrino.entity.Product;
import com.subterrino.service.ColorService;
import com.subterrino.service.ProductService;
import com.subterrino.service.ServiceException;

@ManagedBean(name = "mBeanProduct")
public class MBeanProduct {
	private Integer id;
	private String name;
	private String description;
	private Double price;	
	private Integer idColor;
	
	private static List<Color> colors = new ArrayList<Color>();	
	private static List<Product> products = new ArrayList<Product>();
	static Integer count = 1;
	
	private ApplicationPart[] photoList = new ApplicationPart[9];

	@PostConstruct
	public void loadProducts() {
		try {
			products = new ProductService().list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			colors = new ColorService().list();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String save() throws IOException {
		ArrayList<String> photo_path = new ArrayList<String>();
		try {
			photo_path = ProductService.SavePhotoList(photoList);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
				
		try {
			Product product = new Product();
			product.setId(id);
			product.setName(name);
			product.setDescription(description);
			product.setPrice(price);		
			product.setPhotoList(photo_path);
			
			Color color = new ColorService().search(idColor);
			product.setColor(color);
			
			new ProductService().save(product);
		} catch (Exception e) {
			e.printStackTrace();
		}	
		
		loadProducts();

		return "";
	}

	public String remove(Product product) {
		try {
			new ProductService().remove(product);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		loadProducts();
		return "";
	}

	public String load(Product product) {
		this.id = product.getId();
		this.name = product.getName();
		this.description = product.getDescription();
		this.price = product.getPrice();
		this.idColor = product.getColor().getId();

		return "";
	}

	public String showProduct(Product product) {
		load(product);

		return "product.jsf";
	}
	
	public String getProductImageHTML() {
		String html = "";
		
		if (this.id == null) {
			FacesContext fc = FacesContext.getCurrentInstance();		
			String base_url = fc.getExternalContext().getContextName();
			
			html += "<script type=\"text/javascript\">";
			html += "	window.location.replace(\"/"+base_url+"/index.jsf\");";
			html += "</script>";
		} else {			
			try {
				Product op =  new ProductService().search(this.id);
				ArrayList<String> old_photoList = op.getPhotoList();
				Integer index = 0;
				
				for (int i = 0; i < old_photoList.size()-1; i++) {
					if (!old_photoList.get(i).isEmpty()) {
						String image = "./ServletProductImage?id="+this.id+"&amp;image="+i;
						if (index == 0) {
							html += "<img id=\"full-photo\" src=\""+image+"\"></img>";
							html += "<center><div>";
							index++;
						}
						html += "<img src=\""+image+"\"";
						html += "onclick=\"javascript:$('#full-photo').attr('src','"+image+"');\"";
						html += "></img>";
					}
				}
				
				if (index == 0) {
					html += "<img id=\"full-photo\" src=\"./images/nophoto.jpg\"></img>";
					html += "<center><div>";
				}
				
				html += "</div></center>";
			} catch (Exception e) {
				e.printStackTrace();
			}			
			
		}
		
		return html;
	}
	
	public String getColorName() {		
		try {
			return new ColorService().search(idColor).getName();
		} catch (Exception e) {
			System.err.println("Não foi possível encontrar a cor de id " + idColor);
			return null;
		}
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

	public List<Product> getProducts() {
		return products;
	}

	public static void setProducts(List<Product> products) {
		MBeanProduct.products = products;
	}

	public List<Color> getColors() {
		return colors;
	}

	public static void setColors(List<Color> colors) {
		MBeanProduct.colors = colors;
	}

	public Integer getIdColor() {
		return idColor;
	}

	public void setIdColor(Integer idColor) {
		this.idColor = idColor;
	}
	
	public ApplicationPart[] getPhotoList() {
		return photoList;
	}

	public void setPhoto(ApplicationPart[] photoList) {
		this.photoList = photoList;
	}
}
