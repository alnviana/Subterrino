package com.subterrino.rest;


import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

import com.subterrino.entity.Product;
import com.subterrino.entity.ProductF;
import com.subterrino.service.ProductFacade;

@Path("/ProductFacade")
public class ProductFacadeRest {
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public void save(ProductF productFacade) {
		new ProductFacade().createFullProduct(productFacade);
	}	
}
