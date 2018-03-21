package com.subterrino.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.subterrino.dao.Dao;
import com.subterrino.dao.FactoryDao;
import com.subterrino.entity.CartItem;
import com.subterrino.entity.Product;

@SessionScoped
@ManagedBean(name = "mBeanCart")
public class MBeanCart {

	private static List<CartItem> cart = new ArrayList<CartItem>();
	
	public String addProduct(Integer id) {
		Integer index = -1;
		
		for (int i = 0; i < cart.size(); i++) {
			if (cart.get(i).getProduct().getId() == id) {
				index = i;
				break;
			}
		}
		
		if (index == -1) {
			Dao<Product> productDao = FactoryDao.createProductDao();
			Product product = productDao.search(id);
			
			CartItem ci = new CartItem();
			ci.setProduct(product);
			ci.setCount(1);
			
			cart.add(ci);
		} else {
			CartItem ci = cart.get(index);
			ci.setCount(ci.getCount()+1);
		}
		
		return "cart.jsf";
	}
	
	public String removeProduct(Integer id) {
		for (int i = 0; i < cart.size(); i++) {
			if (cart.get(i).getProduct().getId() == id) {
				cart.remove(i);
				break;
			}
		}
		
		return "";
	}
	
	public String decreaseProduct(Integer id) {
		for (int i = 0; i < cart.size(); i++) {
			if (cart.get(i).getProduct().getId() == id) {
				Integer count = cart.get(i).getCount()-1;
				if (count < 1) {count = 1;}
				cart.get(i).setCount(count);
				break;
			}
		}
		
		return "";
	}
	
	public static void ClearCart() {
		MBeanCart.setCart(new ArrayList<CartItem>());
	}

	public List<CartItem> getCart() {
		return cart;
	}

	public static void setCart(List<CartItem> cart) {
		MBeanCart.cart = cart;
	}
}
