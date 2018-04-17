package com.subterrino.controller;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

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
		try {			
			CartItem originalCT = null;
			List<CartItem> ctList = new CartService().list();
			for (CartItem item : ctList) {
				if (item.getProduct().getId() == id) {
					originalCT = item;
					break;
				}
			}
			
			if (originalCT != null) {
				CartItem ct = new CartItem(originalCT.getProduct());
				ct.setCount(originalCT.getCount() + diff);
				
				try {
					new RestClient<CartItem>().request("http://localhost:8080/Subterrino/rest/Cart", "POST", ct, CartItem.class);
				} catch (Exception e) {
					throw e;
				}
			} else {
				Product p = null;
				List<Product> pList = new RestClient<Product>().request("http://localhost:8080/Subterrino/rest/Product", "GET", null, Product.class);
				for (Product item : pList) {
					if (item.getId() == id) {
						p = item;
						break;
					}
				}
				
				if (p != null) {
					CartItem ct = new CartItem(p);
					ct.setCount(1);
					
					try {
						new RestClient<CartItem>().request("http://localhost:8080/Subterrino/rest/Cart", "POST", ct, CartItem.class);
					} catch (Exception e) {
						throw e;
					}
				} else {
					throw new ServiceException("Produto não encontrado.");
				}
			}			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String removeProduct(Integer id) {
		try {			
			CartItem ct = null;
			List<CartItem> ctList = new CartService().list();
			for (CartItem item : ctList) {
				if (item.getProduct().getId() == id) {
					ct = item;
					break;
				}
			}
			
			if (ct != null) {
				new RestClient<CartItem>().request("http://localhost:8080/Subterrino/rest/Cart", "DELETE", ct, CartItem.class);
			} else {
				throw new ServiceException("Produto não encontrado.");
			}			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "";
	}
	
	public static void ClearCart() {
		new RestClient<CartItem>().request("http://localhost:8080/Subterrino/rest/Cart", "PUT", null, CartItem.class);
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
