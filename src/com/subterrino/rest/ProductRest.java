package com.subterrino.rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.subterrino.entity.Product;
import com.subterrino.service.ProductService;
import com.subterrino.service.ServiceException;

@Path("/Product")
public class ProductRest {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Product> list() {
		try {
			return new ProductService().list();
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public void save(Product product) {
		try {
			new ProductService().save(product);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
	}
	
	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	public void remove(Product product) {
		try {
			new ProductService().remove(product);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
	}
	
}
