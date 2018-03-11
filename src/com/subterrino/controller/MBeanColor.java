package com.subterrino.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

import com.subterrino.dao.ColorDao;
import com.subterrino.entity.Color;

@ManagedBean(name = "mBeanColor")
public class MBeanColor {
	private Integer id;
	private String name;
	
	private static List<Color> colors = new ArrayList<Color>();	
	
	@PostConstruct
	public void loadColors() {
		colors = new ColorDao().list();
	}

	public String save() throws IOException {
		if (id == null || id.equals(0)) {
			add(name);
		} else {
			change(id, name);
		}
		
		loadColors();

		return "";
	}

	private void add(String name) {
		Color color = new Color();
		color.setName(name);
		
		new ColorDao().insert(color);
	}

	private void change(Integer id, String name) {
		for (Color p : colors) {
			if (p.getId().equals(id)) {
				Color color = new Color();
				color.setId(id);
				color.setName(name);
				
				new ColorDao().update(color);
			}
		}
	}

	public String remove(Color color) {
		new ColorDao().remove(color);
		
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
