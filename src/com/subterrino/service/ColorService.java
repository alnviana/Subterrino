package com.subterrino.service;

import java.util.List;

import com.subterrino.dao.FactoryDao;
import com.subterrino.entity.Color;

public class ColorService implements GenericService<Color>{
	
	@Override
	public void save(Color color) throws ServiceException {
		if (color.getName() == null || color.getName() == "") {
			throw new ServiceException("O nome não pode ser nulo ou vazio.");
		}
		
		if (color.getName().length() > 20) {
			throw new ServiceException("O nome não pode ser maior que 20 caracteres.");
		}
		
		if (! FactoryDao.createColorDao().search(Color.class, "name", color.getName()).isEmpty()) {
			throw new ServiceException("Nome já cadastrado.");
		}
		
		try {
			if (color.getId() == null || color.getId().equals(0)) {
				color.setId(null);
				FactoryDao.createColorDao().insert(color);
			} else {
				Color c = FactoryDao.createColorDao().search(Color.class, color.getId());
				
				if (c == null) {
					throw new ServiceException("ID inexistente.");
				} else {
					FactoryDao.createColorDao().update(color);
				}
			}
		} catch (ServiceException e) {
			throw e;
		}		
	}
	
	@Override
	public void remove(Color color) throws ServiceException {		
		FactoryDao.createColorDao().remove(color);	
	}
	
	@Override
	public List<Color> list() throws ServiceException {
		return FactoryDao.createColorDao().list(Color.class);
	}
	
	@Override
	public Color search(Integer id) throws ServiceException {
		return FactoryDao.createColorDao().search(Color.class, id);
	}
}
