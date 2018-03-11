package com.subterrino.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

import com.subterrino.dao.PaymentTypeDao;
import com.subterrino.entity.PaymentType;

@ManagedBean(name = "mBeanPaymentType")
public class MBeanPaymentType {
	private Integer id;
	private String name;
	
	private static List<PaymentType> paymentTypes = new ArrayList<PaymentType>();	
	
	@PostConstruct
	public void loadPaymentTypes() {
		paymentTypes = new PaymentTypeDao().list();
	}

	public String save() throws IOException {
		if (id == null || id.equals(0)) {
			add(name);
		} else {
			change(id, name);
		}
		
		loadPaymentTypes();

		return "";
	}

	private void add(String name) {
		PaymentType paymentType = new PaymentType();
		paymentType.setName(name);
		
		new PaymentTypeDao().insert(paymentType);
	}

	private void change(Integer id, String name) {
		for (PaymentType p : paymentTypes) {
			if (p.getId().equals(id)) {
				PaymentType paymentType = new PaymentType();
				paymentType.setId(id);
				paymentType.setName(name);
				
				new PaymentTypeDao().update(paymentType);
			}
		}
	}

	public String remove(PaymentType paymentType) {
		new PaymentTypeDao().remove(paymentType);
		
		loadPaymentTypes();
		return "";
	}

	public String load(PaymentType paymentType) {
		this.id = paymentType.getId();
		this.name = paymentType.getName();

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

	public List<PaymentType> getPaymentTypes() {
		return paymentTypes;
	}

	public static void setPaymentTypes(List<PaymentType> paymentTypes) {
		MBeanPaymentType.paymentTypes = paymentTypes;
	}
}
