package com.subterrino.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

import com.subterrino.dao.FactoryDao;
import com.subterrino.entity.Color;
import com.subterrino.service.ColorService;

@ManagedBean(name = "mBeanColor")
public class MBeanColor {
	private Integer id;
	private String name;
	
	private static List<Color> colors = new ArrayList<Color>();	
	
	@PostConstruct
	public void loadColors() {
		colors = FactoryDao.createColorDao().list();
	}

	public String save() throws IOException {
		Color c = new Color();
		c.setId(id);
		c.setName(name);
		
		try {
			new ColorService().save(c);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		loadColors();

		return "";
	}

	public String remove(Color color) {
		FactoryDao.createColorDao().remove(color);
		
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
