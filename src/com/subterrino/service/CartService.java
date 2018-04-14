package com.subterrino.service;

import java.util.ArrayList;
import java.util.List;

import com.subterrino.entity.CartItem;

public class CartService implements GenericService<CartItem>{
	
	private static List<CartItem> cart = new ArrayList<CartItem>();

	@Override
	public void save(CartItem ct) throws ServiceException {
		Integer index = -1;
		
		if (ct.getCount() < 1) {
			throw new ServiceException("Quantidade menor que 1 não permitida.");
		}
		
		for (int i = 0; i < cart.size(); i++) {
			if (cart.get(i).getProduct().getId() == ct.getProduct().getId()) {
				index = i;
				break;
			}
		}
		
		if (index == -1) {			
			cart.add(ct);
		} else {
			cart.set(index, ct);
		}
	}

	@Override
	public void remove(CartItem ct) throws ServiceException {
		for (int i = 0; i < cart.size(); i++) {
			if (cart.get(i).getProduct().getId() == ct.getProduct().getId()) {
				cart.remove(i);
				break;
			}
		}		
	}

	@Override
	public List<CartItem> list() throws ServiceException {
		return cart;
	}
	
	@Override
	public CartItem search(Integer id) throws ServiceException {
		for (int i = 0; i < cart.size(); i++) {
			if (cart.get(i).getProduct().getId() == id) {
				return cart.get(i);
			}
		}
		
		throw new ServiceException("Item não encontrado.");
	}
	
	
	public static void ClearCart() {
		CartService.cart = new ArrayList<CartItem>();
	}

}
