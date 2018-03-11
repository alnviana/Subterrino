package com.subterrino.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.subterrino.entity.PaymentType;

public class PaymentTypeDao {
	
	public void insert(PaymentType paymentType) {
		EntityManager em = Connection.getInstance();
		em.getTransaction().begin();
		em.persist(paymentType);
		em.getTransaction().commit();
		em.close();
	}
	
	public void update(PaymentType paymentType) {
		EntityManager em = Connection.getInstance();
		em.getTransaction().begin();
		em.merge(paymentType);
		em.getTransaction().commit();
		em.close();
	}
	
	public void remove(PaymentType paymentType) {
		EntityManager em = Connection.getInstance();
		em.getTransaction().begin();
		em.remove(em.merge(paymentType));
		em.getTransaction().commit();
		em.close();
	}
	
	public PaymentType search(Integer id) {
		EntityManager em = Connection.getInstance();
		PaymentType paymentType = em.find(PaymentType.class, id);
		em.close();
		
		return paymentType;
	}
	
	@SuppressWarnings("unchecked")
	public List<PaymentType> list() {
		EntityManager em = Connection.getInstance();
		Query q = em.createQuery("from PaymentType");
		List<PaymentType> paymentTypes = q.getResultList();
		em.close();
		
		return paymentTypes;
	}
}
