package com.subterrino.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.subterrino.entity.Color;

public class ColorDao extends Dao<Color> {
	
	public List<Color> search(String name) {
		EntityManager em = Connection.getInstance();
		Query q = em.createQuery("From Color Where name like '" + name + "'");
		List<Color> colors = q.getResultList();
		em.close();
		
		return colors;
	}
	
}
