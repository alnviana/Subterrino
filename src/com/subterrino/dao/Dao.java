package com.subterrino.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.subterrino.entity.Color;

public abstract class Dao<T> {
	
	public void insert(T t) {
		EntityManager em = Connection.getInstance();
		em.getTransaction().begin();
		em.persist(t);
		em.getTransaction().commit();
		em.close();
	}
	
	public void update(T t) {
		EntityManager em = Connection.getInstance();
		em.getTransaction().begin();
		em.merge(t);
		em.getTransaction().commit();
		em.close();
	}
	
	public void remove(T t) {
		EntityManager em = Connection.getInstance();
		em.getTransaction().begin();
		em.remove(em.merge(t));
		em.getTransaction().commit();
		em.close();
	}
	
	public T search(Class<T> c, Integer id) {
		EntityManager em = Connection.getInstance();
		T t = em.find(c, id);
		em.close();
		
		return t;
	}
	
	public List<T> search(Class<T> c, String propertyName, Object value) {
		EntityManager em = Connection.getInstance();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<T> cq = cb.createQuery(c);
		Root<T> root = cq.from(c);
		cq.where(cb.equal(root.get(propertyName), value));		
		List<T> t = em.createQuery(cq).getResultList();
		em.close();
		
		return t;
	}
	
	@SuppressWarnings("unchecked")
	public List<T> list(Class<T> c) {
		EntityManager em = Connection.getInstance();
		Query q = em.createQuery("from " + c.getSimpleName());
		List<T> t = q.getResultList();
		em.close();
		
		return t;
	}
	
}
