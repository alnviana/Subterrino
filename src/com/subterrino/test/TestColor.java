package com.subterrino.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.subterrino.entity.Color;
import com.subterrino.service.ColorService;

public class TestColor {

	@Test
	public void mustCreateColor() {
		// Everything null, should result in error
		Color c = new Color();
		c.setId(null);
		c.setName(null);
		
		try {
			new ColorService().save(c);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		assertEquals(false, c.getId() != null);
		
		// Empty name, should result in error
		c = new Color();
		c.setId(null);
		c.setName("");
		
		try {
			new ColorService().save(c);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		assertEquals(false, c.getId() != null);
		
		// Only id null, should save
		c = new Color();
		c.setId(null);
		c.setName("Teste");
		
		try {
			new ColorService().save(c);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		assertEquals(true, c.getId() != null);
		
		//Again, only id null, but should result in error
		c = new Color();
		c.setId(null);
		c.setName("Teste");
		
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
	}
}
