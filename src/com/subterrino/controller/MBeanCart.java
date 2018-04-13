package com.subterrino.controller;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.subterrino.dao.FactoryDao;
import com.subterrino.entity.CartItem;
import com.subterrino.entity.Product;
import com.subterrino.service.CartService;
import com.subterrino.service.ServiceException;

@SessionScoped
@ManagedBean(name = "mBeanCart")
public class MBeanCart {
	
	public String addProduct(Integer id) {
		changeCartItemCount(id, 1);	
		return "cart.jsf";
	}
	
	public String decreaseProduct(Integer id) {
		changeCartItemCount(id, -1);		
		return "";
	}
	
	private void changeCartItemCount(Integer id, Integer diff) {
		CartService cs = new CartService();
		CartItem ct;
		
		try {
			try {				
				CartItem ct2 = cs.search(id);				
				ct2 = cs.search(id);
				
				ct = new CartItem(ct2.getProduct());
				ct.setCount(ct2.getCount() + diff);
			} catch (Exception e) {
				Product p = FactoryDao.createProductDao().search(Product.class, id);
				
				if (p == null) {
					throw new ServiceException("Produto não encontrado.");
				}
				
				ct = new CartItem(p);
				ct.setCount(1);
			}
			
			try {
				
				new CartService().save(ct);
			} catch (Exception e) {
				throw e;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String removeProduct(Integer id) {
		try {
			Product p = FactoryDao.createProductDao().search(Product.class, id);
			
			if (p == null) {
				throw new ServiceException("Produto não encontrado.");
			}
			
			CartItem ct = new CartItem(p);
			ct.setCount(0);
			
			new CartService().remove(ct);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "";
	}
	
	public static void ClearCart() {
		CartService.ClearCart();
	}

	public List<CartItem> getCart() {
		try {
			return new CartService().list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;	
	}
}
