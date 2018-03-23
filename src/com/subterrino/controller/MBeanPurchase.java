package com.subterrino.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.subterrino.dao.Dao;
import com.subterrino.dao.FactoryDao;
import com.subterrino.entity.CartItem;
import com.subterrino.entity.PaymentType;
import com.subterrino.entity.Purchase;
import com.subterrino.entity.PurchaseItem;

@SessionScoped
@ManagedBean(name = "mBeanPurchase")
public class MBeanPurchase {
	private Integer id;
	private String name;
	private String address;
	private Integer add_num;
	private String phone;
	private Integer paymentTypeID;
	
	private List<PurchaseItem> purchaseItems = new ArrayList<PurchaseItem>();
	private static List<Purchase> purchases = new ArrayList<Purchase>();
	private static List<PaymentType> paymentTypes = new ArrayList<PaymentType>();	
	
	@PostConstruct
	public void loadPurchases() {
		purchases = FactoryDao.createPurchaseDao().list(Purchase.class);
		paymentTypes = FactoryDao.createPaymentTypeDao().list(PaymentType.class);
	}
	
	public String showPurchase(ArrayList<CartItem> cart) {		
		if (cart.size() > 0) {
			purchaseItems = new ArrayList<PurchaseItem>();
			
			for (CartItem cartItem : cart) {
				PurchaseItem pi = new PurchaseItem();
				pi.setProduct(cartItem.getProduct());
				pi.setCount(cartItem.getCount());
				
				purchaseItems.add(pi);
			}			
			
			return "purchase.jsf";
		}else {
			return "index.jsf";
		}		
	}
	
	public Double purchaseTotal() {
		Double totalPrice = (double) 0;
		for (PurchaseItem purchaseItem : purchaseItems) {
			totalPrice += purchaseItem.getTotal();
		}
		
		return totalPrice;
	}

	public String save() throws IOException {
		Purchase purchase = new Purchase();
		purchase.setName(name);
		purchase.setAddress(address);
		purchase.setAdd_num(add_num);
		purchase.setPhone(phone);
		
		Dao<PaymentType> ptd = FactoryDao.createPaymentTypeDao();
		PaymentType pt = ptd.search(PaymentType.class, paymentTypeID);
		purchase.setPaymentType(pt);
		
		for (PurchaseItem pi : purchaseItems) {
			pi.setPurchase(purchase);
		}
		
		purchase.setPurchaseItems(purchaseItems);		
		FactoryDao.createPurchaseDao().insert(purchase);
		
		name = null;
		address = null;
		add_num = null;
		phone = null;
		paymentTypeID = 0;
		purchaseItems = new ArrayList<PurchaseItem>();
		
		MBeanCart.ClearCart();
		loadPurchases();

		return "purchase_manager.jsf";
	}
	
	public String remove(Purchase purchase) {
		FactoryDao.createPurchaseDao().remove(purchase);
		
		loadPurchases();
		return "";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Purchase> getPurchases() {
		return purchases;
	}

	public static void setPurchases(List<Purchase> purchases) {
		MBeanPurchase.purchases = purchases;
	}

	public List<PurchaseItem> getPurchaseItems() {
		return purchaseItems;
	}

	public void setPurchaseItems(List<PurchaseItem> purchaseItems) {
		this.purchaseItems = purchaseItems;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getAdd_num() {
		return add_num;
	}

	public void setAdd_num(Integer add_num) {
		this.add_num = add_num;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Integer getPaymentTypeID() {
		return paymentTypeID;
	}

	public void setPaymentTypeID(Integer paymentTypeID) {
		this.paymentTypeID = paymentTypeID;
	}

	public List<PaymentType> getPaymentTypes() {
		return paymentTypes;
	}

	public static void setPaymentTypes(List<PaymentType> paymentTypes) {
		MBeanPurchase.paymentTypes = paymentTypes;
	}
}
