package com.subterrino.rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.subterrino.entity.Purchase;
import com.subterrino.service.PurchaseService;
import com.subterrino.service.ServiceException;

@Path("/Purchase")
public class PurchaseRest {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Purchase> list() {
		try {
			return new PurchaseService().list();
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public void save(Purchase purchase) {
		try {
			new PurchaseService().save(purchase);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
	}
	
	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	public void remove(Purchase purchase) {
		try {
			new PurchaseService().remove(purchase);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
	}
	
}
