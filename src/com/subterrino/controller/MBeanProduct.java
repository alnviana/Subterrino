package com.subterrino.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.apache.catalina.core.ApplicationPart;

import com.subterrino.dao.Dao;
import com.subterrino.dao.FactoryDao;
import com.subterrino.entity.Color;
import com.subterrino.entity.PaymentType;
import com.subterrino.entity.Product;

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

	public void saveT() {
		add("Xiaomi Mi Mix Phone com 6 GB de RAM, ROM de 256 GB, Dual SIM - Preto",
				"6.4 \\\\\\\", Google Android 6.0.1 (Marshmallow), Quad-Core, Qualcomm snapdragon 821 MSM8996AC",
				1791.00, 1, new ArrayList<String>(Collections.nCopies(10, "")));
		add("Xiaomi Mi 6 4G", "Snapdragon 835, Splash Resistant, Dual, 12MP, Rear Cameras", 1404.60, 2, new ArrayList<String>(Collections.nCopies(10, "")));
		
		loadProducts();
	}
	
	@PostConstruct
	public void loadProducts() {
		products = FactoryDao.createProductDao().list(Product.class);
		colors = FactoryDao.createColorDao().list(Color.class);
	}

	public String save() throws IOException {
		ArrayList<String> photo_path = new ArrayList<String>();
		
		for(ApplicationPart photo : photoList) {
			if (photo != null && photo.getSize() > 0) {
				byte[] bytes = new byte[(int) photo.getSize()];
				photo.getInputStream().read(bytes);
				
				Timestamp timestamp = new Timestamp(System.currentTimeMillis());
				photo_path.add("C:/temp/images/" + timestamp.getTime() + "-" + photo.getSubmittedFileName());
				File f = new File(photo_path.get(photo_path.size()-1));
				
				FileOutputStream fo = new FileOutputStream(f);
				fo.write(bytes);
				fo.close();
			}else {
				photo_path.add("");
			}
		}

		if (id == null || id.equals(0)) {
			add(name, description, price, idColor, photo_path);
		} else {
			change(id, name, description, price, idColor, photo_path);
		}
		
		loadProducts();

		return "";
	}

	private void add(String name, String description, Double price, Integer idColor, ArrayList<String> photo_path) {
		Dao<Color> colorDao = FactoryDao.createColorDao();
		Color color = colorDao.search(Color.class, idColor);
		
		
		Product product = new Product();
		product.setName(name);
		product.setDescription(description);
		product.setPrice(price);
		product.setColor(color);
		product.setPhotoList(photo_path);
		
		FactoryDao.createProductDao().insert(product);
	}

	private void change(Integer id, String name, String description, Double price, Integer idColor, ArrayList<String> photo_path) {
		for (Product p : products) {
			if (p.getId().equals(id)) {
				Product product = new Product();
				product.setId(id);
				product.setName(name);
				product.setDescription(description);
				product.setPrice(price);
				
				Dao<Color> colorDao = FactoryDao.createColorDao();
				Color color = colorDao.search(Color.class, idColor);
				product.setColor(color);
				
				Product op = FactoryDao.createProductDao().search(Product.class, new Integer(id));
				ArrayList<String> old_photoList = op.getPhotoList();
				for(int i = 0; i < photo_path.size(); i++) {
					if (!photo_path.get(i).isEmpty()) {
						old_photoList.set(i, photo_path.get(i));
					}
				}
				product.setPhotoList(old_photoList);
				
				FactoryDao.createProductDao().update(product);
			}
		}
	}

	public String remove(Product product) {
		FactoryDao.createProductDao().remove(product);
		
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
			Product op = FactoryDao.createProductDao().search(Product.class, new Integer(id));
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
			html += "</div></center>";
			
			
			html += "";
			html += "";
			html += "";
		}
		
		return html;
	}
	
	public String getColorName() {		
		return FactoryDao.createColorDao().search(Color.class, idColor).getName();
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
