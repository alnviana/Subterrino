package com.subterrino.test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.runners.MethodSorters;
import org.junit.FixMethodOrder;
import org.junit.Test;

import com.subterrino.entity.PaymentType;
import com.subterrino.service.PaymentTypeService;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestPaymentType {
	
	static Integer id;

	@Test
	public void aMustCreatePaymentType() {
		//Everything null, should result in error
		PaymentType c = new PaymentType();
		c.setId(null);
		c.setName(null);		
		try {
			new PaymentTypeService().save(c);
		} catch (Exception e) {
			e.printStackTrace();
		}		
		assertEquals(false, c.getId() != null);
		
		//Empty name, should result in error
		c = new PaymentType();
		c.setId(null);
		c.setName("");		
		try {
			new PaymentTypeService().save(c);
		} catch (Exception e) {
			e.printStackTrace();
		}		
		assertEquals(false, c.getId() != null);
		
		//Name with more than 20 characters, should result in error
		c = new PaymentType();
		c.setId(null);
		c.setName("asfghjklçqwertyuiopzxcvbnmqwertyuiopasdfghjklllzxcvbnm");		
		try {
			new PaymentTypeService().save(c);
		} catch (Exception e) {
			e.printStackTrace();
		}		
		assertEquals(false, c.getId() != null);
		
		//Only id null, should save
		c = new PaymentType();
		c.setId(null);
		c.setName("Teste1222");		
		try {
			new PaymentTypeService().save(c);
		} catch (Exception e) {
			e.printStackTrace();
		}		
		assertEquals(true, c.getId() != null);
		
		TestPaymentType.id = c.getId(); //Save ID to use in more tests
		
		//Again, only id null, but should fail because this name already exists
		c = new PaymentType();
		c.setId(null);
		c.setName("Teste1222");		
		try {
			new PaymentTypeService().save(c);
		} catch (Exception e) {
			e.printStackTrace();
		}		
		assertEquals(false, c.getId() != null);
	}
	
	@Test
	public void bMustAlterPaymentType() {
		//To change, we always need to give the ID.
		
		PaymentType c = new PaymentType();
		PaymentTypeService cs = new PaymentTypeService();
		
		
		//Everything null, should result in error
		c = new PaymentType();
		c.setId(null);
		c.setName(null);		
		try {
			new PaymentTypeService().save(c);
		} catch (Exception e) {
			e.printStackTrace();
		}		
		assertEquals(false, c.getId() != null);
		
		//Empty name, should result in error
		c = new PaymentType();
		c.setId(TestPaymentType.id);
		c.setName("");		
		try {
			new PaymentTypeService().save(c);
		} catch (Exception e) {
			e.printStackTrace();
		}		
		try {
			c = cs.search(TestPaymentType.id);
		} catch (Exception e) {
			e.printStackTrace();
		}	
		assertEquals(false, c.getName().isEmpty());
		
		//Name with more than 20 characters, should result in error
		c = new PaymentType();
		c.setId(TestPaymentType.id);
		c.setName("asfghjklçqwertyuiopzxcvbnmqwertyuiopasdfghjklllzxcvbnm");		
		try {
			new PaymentTypeService().save(c);
		} catch (Exception e) {
			e.printStackTrace();
		}		
		try {
			c = cs.search(TestPaymentType.id);
		} catch (Exception e) {
			e.printStackTrace();
		}	
		assertEquals(false, c.getName().length() > 20);
		
		//Saving with some name already on db
		c = new PaymentType();
		c.setId(TestPaymentType.id);
		c.setName("Teste1222");		
		Boolean error = false;
		try {
			cs.save(c);
		} catch (Exception e) {
			e.printStackTrace();
			error = true;
		}		
		assertEquals(true, error);
		
		//Saving with with inexistent ID
		c = new PaymentType();
		c.setId(99999);
		c.setName("Teste1222");		
		error = false;
		try {
			cs.save(c);
		} catch (Exception e) {
			e.printStackTrace();
			error = true;
		}		
		assertEquals(true, error);
		
		//Saving with another name, should save
		c = new PaymentType();
		c.setId(TestPaymentType.id);
		c.setName("Teste1765");		
		try {
			cs.save(c);
			c = cs.search(TestPaymentType.id);
		} catch (Exception e) {
			e.printStackTrace();
		}		
		assertEquals(true, c.getName().equals("Teste1765"));		
	}
	
	@Test
	public void cMustListPaymentType() {
		List<PaymentType> cList = new ArrayList<PaymentType>();
		try {
			cList = new PaymentTypeService().list();
		} catch (Exception e) {
			e.printStackTrace();
		}		
		assertEquals(true, cList.size() > 0);
	}
	
	@Test
	public void dMustDeletePaymentType() {
		PaymentType c = new PaymentType();
		PaymentTypeService cs = new PaymentTypeService();
		
		//Correct ID, should delete
		c.setId(TestPaymentType.id);
		try {
			cs.remove(c);
			c = cs.search(TestPaymentType.id);
		} catch (Exception e) {
			e.printStackTrace();
		}		
		assertEquals(true, c == null);
	}
}
