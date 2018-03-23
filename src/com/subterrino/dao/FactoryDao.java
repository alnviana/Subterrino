package com.subterrino.dao;

import com.subterrino.entity.Color;
import com.subterrino.entity.PaymentType;
import com.subterrino.entity.Product;
import com.subterrino.entity.Purchase;

public class FactoryDao {
	
	public static Dao<Color> createColorDao() {
		return new GenericDao<Color>();
	}
	
	public static Dao<PaymentType> createPaymentTypeDao() {
		return new GenericDao<PaymentType>();
	}
	
	public static Dao<Product> createProductDao() {
		return new GenericDao<Product>();
	}
	
	public static Dao<Purchase> createPurchaseDao() {
		return new GenericDao<Purchase>();
	}
	
}
