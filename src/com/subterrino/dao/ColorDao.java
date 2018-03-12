package com.subterrino.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.subterrino.entity.Color;

public class ColorDao implements Dao<Color> {
	
	@Override
	public void insert(Color color) {
		EntityManager em = Connection.getInstance();
		em.getTransaction().begin();
		em.persist(color);
		em.getTransaction().commit();
		em.close();
	}
	
	@Override
	public void update(Color color) {
		EntityManager em = Connection.getInstance();
		em.getTransaction().begin();
		em.merge(color);
		em.getTransaction().commit();
		em.close();
	}
	
	@Override
	public void remove(Color color) {
		EntityManager em = Connection.getInstance();
		em.getTransaction().begin();
		em.remove(em.merge(color));
		em.getTransaction().commit();
		em.close();
	}
	
	@Override
	public Color search(Integer id) {
		EntityManager em = Connection.getInstance();
		Color color = em.find(Color.class, id);
		em.close();
		
		return color;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Color> list() {
		EntityManager em = Connection.getInstance();
		Query q = em.createQuery("from Color");
		List<Color> colors = q.getResultList();
		em.close();
		
		return colors;
	}
}
