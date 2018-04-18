package com.subterrino.test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.runners.MethodSorters;
import org.junit.FixMethodOrder;
import org.junit.Test;

import com.subterrino.entity.CartItem;
import com.subterrino.entity.Color;
import com.subterrino.entity.Product;
import com.subterrino.service.CartService;
import com.subterrino.service.ColorService;
import com.subterrino.service.ProductService;
import com.subterrino.service.ServiceException;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestCart {
	
	@Test
	public void aMustCreateCartItem() {
		Product product = new Product();
		try {
			product = new ProductService().list().get(0);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		// Item count < 0, should get error
		Boolean found = false;
		CartItem a = new CartItem(product);
		a.setCount(0);
		try {
			new CartService().save(a);
			
			List<CartItem> cl = new CartService().list();
			for (CartItem cartItem : cl) {
				if (cartItem.getProduct().getId() == product.getId()) {
					found = true;
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}		
		assertEquals(false, found);
		
		// Count > 0, should save
		found = false;
		a = new CartItem(product);
		a.setCount(1);
		try {
			new CartService().save(a);
			
			List<CartItem> cl = new CartService().list();
			for (CartItem cartItem : cl) {
				if (cartItem.getProduct().getId() == product.getId()) {
					found = true;
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}		
		assertEquals(true, found);
	}
	
	@Test
	public void bMustAlterCartItem() {
		Product product = new Product();
		try {
			product = new ProductService().list().get(0);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// Item count < 0, should get error
		Boolean found = false;
		CartItem a = new CartItem(product);
		a.setCount(0);
		try {
			new CartService().save(a);
			
			List<CartItem> cl = new CartService().list();
			for (CartItem cartItem : cl) {
				if (cartItem.getProduct().getId() == product.getId()) {
					found = true;
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}		
		assertEquals(false, found);
		
		// Item count > 0, should save
		found = false;
		a = new CartItem(product);
		a.setCount(3);
		try {
			new CartService().save(a);
			
			List<CartItem> cl = new CartService().list();
			for (CartItem cartItem : cl) {
				if (cartItem.getProduct().getId() == product.getId()) {
					found = true;
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}		
		assertEquals(true, found);
	}
	
	@Test
	public void cMustListCartItem() {
		List<CartItem> cList = new ArrayList<CartItem>();
		try {
			cList = new CartService().list();
		} catch (Exception e) {
			e.printStackTrace();
		}		
		assertEquals(true, cList.size() > 0);
	}
	
	@Test
	public void dMustDeleteCartItem() {
		Product product = new Product();
		try {
			product = new ProductService().list().get(0);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// Tem que deletar
		Boolean found = false;
		CartItem a = new CartItem(product);
		try {			
			new CartService().remove(a);
			
			List<CartItem> cl = new CartService().list();
			for (CartItem cartItem : cl) {
				if (cartItem.getProduct().getId() == product.getId()) {
					found = true;
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}		
		assertEquals(false, found);
	}
}
