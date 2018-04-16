package com.subterrino.rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.subterrino.entity.PaymentType;
import com.subterrino.service.PaymentTypeService;
import com.subterrino.service.ServiceException;

@Path("/PaymentType")
public class PaymentTypeRest {
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<PaymentType> list() {
		try {
			return new PaymentTypeService().list();
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public void save(PaymentType paymentType) {
		try {
			new PaymentTypeService().save(paymentType);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
	}
	
	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	public void remove(PaymentType paymentType) {
		try {
			new PaymentTypeService().remove(paymentType);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
	}
	
}
