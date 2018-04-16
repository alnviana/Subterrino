package com.subterrino.rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.subterrino.entity.CartItem;
import com.subterrino.service.CartService;
import com.subterrino.service.ServiceException;

@Path("/Cart")
public class CartRest {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<CartItem> list() {
		try {
			return new CartService().list();
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public void save(CartItem cartItem) {
		try {
			new CartService().save(cartItem);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
	}
	
	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	public void remove(CartItem cartItem) {
		try {
			new CartService().remove(cartItem);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
	}
	
}
