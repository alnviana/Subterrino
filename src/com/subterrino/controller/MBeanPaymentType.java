package com.subterrino.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

import com.subterrino.entity.PaymentType;
import com.subterrino.service.PaymentTypeService;

@ManagedBean(name = "mBeanPaymentType")
public class MBeanPaymentType {
	private Integer id;
	private String name;
	
	private static List<PaymentType> paymentTypes = new ArrayList<PaymentType>();	
	
	@PostConstruct
	public void loadPaymentTypes() {
		try {
			paymentTypes = new PaymentTypeService().list();
		} catch (Exception e) {
			System.err.println("Não foi possível carregar a lista de tipos de pagamentos.");
		}
	}

	public String save() throws IOException {
		PaymentType pt = new PaymentType();
		pt.setId(id);
		pt.setName(name);
		
		try {
			new PaymentTypeService().save(pt);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		loadPaymentTypes();

		return "";
	}

	public String remove(PaymentType paymentType) {
		try {
			new PaymentTypeService().remove(paymentType);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
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
