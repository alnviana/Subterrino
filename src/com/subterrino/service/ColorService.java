package com.subterrino.service;

import com.subterrino.dao.ColorDao;
import com.subterrino.entity.Color;

public class ColorService {
	
	public void save(Color color) throws ServiceException {
		if ((color.getId() == null || color.getId().equals(0)) && (color.getName() == null || color.getName() == "")) {
			throw new ServiceException("O nome não pode ser nulo ou vazio.");
		}
		
		if (color.getName().length() > 20) {
			throw new ServiceException("O nome não pode ser maior que 20 caracteres.");
		}
		
		if (! new ColorDao().search(color.getName()).isEmpty()) {
			throw new ServiceException("Nome já cadastrado.");
		}
		
		try {
			if (color.getId() == null || color.getId().equals(0)) {
				new ColorDao().insert(color);
			} else {
				Color c = new ColorDao().search(color.getId());
				
				if (c == null) {
					throw new ServiceException("ID inexistente.");
				} else {
					new ColorDao().update(color);
				}
			}
		} catch (ServiceException e) {
			throw e;
		}		
	}
	
}
