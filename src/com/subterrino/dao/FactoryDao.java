package com.subterrino.dao;

import com.subterrino.entity.Color;
import com.subterrino.entity.PaymentType;
import com.subterrino.entity.Product;
import com.subterrino.entity.Purchase;

public class FactoryDao {
	
	public static Dao<Color> createColorDao() {
		return new ColorDao();
	}
	
	public static Dao<PaymentType> createPaymentTypeDao() {
		return new PaymentTypeDao();
	}
	
	public static Dao<Product> createProductDao() {
		return new ProductDao();
	}
	
	public static Dao<Purchase> createPurchaseDao() {
		return new PurchaseDao();
	}
	
}
