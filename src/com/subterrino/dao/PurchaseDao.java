package com.subterrino.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.subterrino.entity.Purchase;

public class PurchaseDao {
	
	public void insert(Purchase purchase) {
		EntityManager em = Connection.getInstance();
		em.getTransaction().begin();
		em.persist(purchase);
		em.getTransaction().commit();
		em.close();
	}
	
	public void update(Purchase purchase) {
		EntityManager em = Connection.getInstance();
		em.getTransaction().begin();
		em.merge(purchase);
		em.getTransaction().commit();
		em.close();
	}
	
	public void remove(Purchase purchase) {
		EntityManager em = Connection.getInstance();
		em.getTransaction().begin();
		em.remove(em.merge(purchase));
		em.getTransaction().commit();
		em.close();
	}
	
	public Purchase search(Integer id) {
		EntityManager em = Connection.getInstance();
		Purchase purchase = em.find(Purchase.class, id);
		em.close();
		
		return purchase;
	}
	
	@SuppressWarnings("unchecked")
	public List<Purchase> list() {
		EntityManager em = Connection.getInstance();
		Query q = em.createQuery("from Purchase");
		List<Purchase> purchases = q.getResultList();
		em.close();
		
		return purchases;
	}
}
