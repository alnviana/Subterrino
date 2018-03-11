package com.subterrino.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Purchase {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	private String name;
	private String address;
	private Integer add_num;
	private String phone;
	
	@ManyToOne
	@JoinColumn(name="FK_PAYMENTTYPE")
	private PaymentType paymentType;
	
	@OneToMany(mappedBy="purchase",cascade=CascadeType.ALL, fetch = FetchType.EAGER)
	private List<PurchaseItem> purchaseItems;
	
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
	public PaymentType getPaymentType() {
		return paymentType;
	}
	public void setPaymentType(PaymentType paymentType) {
		this.paymentType = paymentType;
	}
	public List<PurchaseItem> getPurchaseItems() {
		return purchaseItems;
	}
	public void setPurchaseItems(List<PurchaseItem> purchaseItems) {
		this.purchaseItems = purchaseItems;
	}
}
