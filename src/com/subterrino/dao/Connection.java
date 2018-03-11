package com.subterrino.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Connection {
	static EntityManagerFactory emf = Persistence.createEntityManagerFactory("subterrino");
	
	public static EntityManager getInstance() {
		return emf.createEntityManager();
	}
}