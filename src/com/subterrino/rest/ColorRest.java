package com.subterrino.rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.subterrino.entity.Color;
import com.subterrino.service.ColorService;
import com.subterrino.service.ServiceException;

@Path("/Color")
public class ColorRest {
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Color> list() {
		try {
			return new ColorService().list();
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public void save(Color color) {
		try {
			new ColorService().save(color);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
	}
	
	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	public void remove(Color color) {
		try {
			new ColorService().remove(color);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
	}
	
}
