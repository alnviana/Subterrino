package com.subterrino.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

import com.subterrino.entity.Color;

@ManagedBean(name = "mBeanColor")
public class MBeanColor {
	private Integer id;
	private String name;
	
	private static List<Color> colors = new ArrayList<Color>();	
	
	@PostConstruct
	public void loadColors() {
		try {
			colors = new RestClient<Color>().request("http://localhost:8080/Subterrino/rest/Color", "GET", null, Color.class);
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}

	public String save() throws IOException {
		Color c = new Color();
		c.setId(id);
		c.setName(name);
		
		try {
			new RestClient<Color>().request("http://localhost:8080/Subterrino/rest/Color", "POST", c, Color.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		loadColors();

		return "";
	}

	public String remove(Color color) throws IOException {		
		try {
			new RestClient<Color>().request("http://localhost:8080/Subterrino/rest/Color", "DELETE", color, Color.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		loadColors();
		return "";
	}

	public String load(Color color) {
		this.id = color.getId();
		this.name = color.getName();

		return "";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Color> getColors() {
		return colors;
	}

	public static void setColors(List<Color> colors) {
		MBeanColor.colors = colors;
	}
}
