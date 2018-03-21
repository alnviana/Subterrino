package com.subterrino.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

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
	
	@SuppressWarnings("unchecked")
	public List<T> list(Class<T> c) {
		EntityManager em = Connection.getInstance();
		Query q = em.createQuery("from " + c.getSimpleName());
		List<T> t = q.getResultList();
		em.close();
		
		return t;
	}
	
	//Necessário tornar os métodos daqui genérico
	//Remover instanciamento direto sem Factory da classe ColorService
	//Corrigir MBeans
}
