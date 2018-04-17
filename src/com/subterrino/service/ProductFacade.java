package com.subterrino.service;

import java.util.ArrayList;
import java.util.List;

import com.subterrino.entity.Color;
import com.subterrino.entity.Product;
import com.subterrino.entity.ProductF;

public class ProductFacade {
	public void createFullProduct(ProductF pf) {
		ColorService cs = new ColorService();
		Color c = null;
		
		try {
			List<Color> cList = cs.list();
			for (Color color2 : cList) {
				if (color2.getName().equals(pf.getColor().getName())) {
					c = color2;
					break;
				}
			}
			
			try {
				if (c == null) {
					c = pf.getColor();
					cs.save(c);
				}
				
				pf.setColor(c);
				
				Product p = new Product();
				p.setColor(c);
				p.setDescription(pf.getDescription());
				p.setId(pf.getId());
				p.setName(pf.getName());
				p.setPrice(pf.getPrice());
				
				ArrayList<String> photo_path = new ArrayList<String>();
				try {
					photo_path = ProductService.SavePhotoList(pf.getPhotoList());
				} catch (ServiceException e) {
					e.printStackTrace();
				}
				
				p.setPhotoList(photo_path);
				
				
				new ProductService().save(p);
			} catch (Exception e) {
				e.printStackTrace();
			}	
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
}
