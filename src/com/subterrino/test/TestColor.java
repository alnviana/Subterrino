package com.subterrino.test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.runners.MethodSorters;
import org.junit.FixMethodOrder;
import org.junit.Test;

import com.subterrino.entity.Color;
import com.subterrino.service.ColorService;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestColor {
	
	static Integer id;

	@Test
	public void aMustCreateColor() {
		//Everything null, should result in error
		Color c = new Color();
		c.setId(null);
		c.setName(null);		
		try {
			new ColorService().save(c);
		} catch (Exception e) {
			e.printStackTrace();
		}		
		assertEquals(false, c.getId() != null);
		
		//Empty name, should result in error
		c = new Color();
		c.setId(null);
		c.setName("");		
		try {
			new ColorService().save(c);
		} catch (Exception e) {
			e.printStackTrace();
		}		
		assertEquals(false, c.getId() != null);
		
		//Name with more than 20 characters, should result in error
		c = new Color();
		c.setId(null);
		c.setName("asfghjklçqwertyuiopzxcvbnmqwertyuiopasdfghjklllzxcvbnm");		
		try {
			new ColorService().save(c);
		} catch (Exception e) {
			e.printStackTrace();
		}		
		assertEquals(false, c.getId() != null);
		
		//Only id null, should save
		c = new Color();
		c.setId(null);
		c.setName("Teste1222");		
		try {
			new ColorService().save(c);
		} catch (Exception e) {
			e.printStackTrace();
		}		
		assertEquals(true, c.getId() != null);
		
		TestColor.id = c.getId(); //Save ID to use in more tests
		
		//Again, only id null, but should fail because this name already exists
		c = new Color();
		c.setId(null);
		c.setName("Teste1222");		
		try {
			new ColorService().save(c);
		} catch (Exception e) {
			e.printStackTrace();
		}		
		assertEquals(false, c.getId() != null);
	}
	
	@Test
	public void bMustAlterColor() {
		//To change, we always need to give the ID.
		
		Color c = new Color();
		ColorService cs = new ColorService();
		
		
		//Everything null, should result in error
		c = new Color();
		c.setId(null);
		c.setName(null);		
		try {
			new ColorService().save(c);
		} catch (Exception e) {
			e.printStackTrace();
		}		
		assertEquals(false, c.getId() != null);
		
		//Empty name, should result in error
		c = new Color();
		c.setId(TestColor.id);
		c.setName("");		
		try {
			new ColorService().save(c);
		} catch (Exception e) {
			e.printStackTrace();
		}		
		try {
			c = cs.search(TestColor.id);
		} catch (Exception e) {
			e.printStackTrace();
		}	
		assertEquals(false, c.getName().isEmpty());
		
		//Name with more than 20 characters, should result in error
		c = new Color();
		c.setId(TestColor.id);
		c.setName("asfghjklçqwertyuiopzxcvbnmqwertyuiopasdfghjklllzxcvbnm");		
		try {
			new ColorService().save(c);
		} catch (Exception e) {
			e.printStackTrace();
		}		
		try {
			c = cs.search(TestColor.id);
		} catch (Exception e) {
			e.printStackTrace();
		}	
		assertEquals(false, c.getName().length() > 20);
		
		//Saving with some name already on db
		c = new Color();
		c.setId(TestColor.id);
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
		c = new Color();
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
		c = new Color();
		c.setId(TestColor.id);
		c.setName("Teste1765");		
		try {
			cs.save(c);
			c = cs.search(TestColor.id);
		} catch (Exception e) {
			e.printStackTrace();
		}		
		assertEquals(true, c.getName().equals("Teste1765"));		
	}
	
	@Test
	public void cMustListColor() {
		List<Color> cList = new ArrayList<Color>();
		try {
			cList = new ColorService().list();
		} catch (Exception e) {
			e.printStackTrace();
		}		
		assertEquals(true, cList.size() > 0);
	}
	
	@Test
	public void dMustDeleteColor() {
		Color c = new Color();
		ColorService cs = new ColorService();
		
		//Correct ID, should delete
		c.setId(TestColor.id);
		try {
			cs.remove(c);
			c = cs.search(TestColor.id);
		} catch (Exception e) {
			e.printStackTrace();
		}		
		assertEquals(true, c == null);
	}
}
