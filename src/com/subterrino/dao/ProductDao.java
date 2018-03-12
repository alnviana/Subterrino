package com.subterrino.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.subterrino.entity.Product;

public class ProductDao implements Dao<Product> {
	
	@Override
	public void insert(Product product) {
		EntityManager em = Connection.getInstance();
		em.getTransaction().begin();
		em.persist(product);
		em.getTransaction().commit();
		em.close();
	}
	
	@Override
	public void update(Product product) {
		EntityManager em = Connection.getInstance();
		em.getTransaction().begin();
		em.merge(product);
		em.getTransaction().commit();
		em.close();
	}
	
	@Override
	public void remove(Product product) {
		EntityManager em = Connection.getInstance();
		em.getTransaction().begin();
		em.remove(em.merge(product));
		em.getTransaction().commit();
		em.close();
	}
	
	@Override
	public Product search(Integer id) {
		EntityManager em = Connection.getInstance();
		Product product = em.find(Product.class, id);
		em.close();
		
		return product;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Product> list() {
		EntityManager em = Connection.getInstance();
		Query q = em.createQuery("from Product");
		List<Product> products = q.getResultList();
		em.close();
		
		return products;
	}
}
