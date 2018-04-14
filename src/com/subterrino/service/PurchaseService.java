package com.subterrino.service;

import java.util.List;

import com.subterrino.dao.FactoryDao;
import com.subterrino.entity.Purchase;

public class PurchaseService implements GenericService<Purchase>{

	@Override
	public void save(Purchase purchase) throws ServiceException {
		try {
			if (purchase.getId() == null || purchase.getId().equals(0)) {
				purchase.setId(null);
				FactoryDao.createPurchaseDao().insert(purchase);
			} else {
				Purchase p = FactoryDao.createPurchaseDao().search(Purchase.class, purchase.getId());
				
				if (p == null) {
					throw new ServiceException("ID inexistente.");
				} else {					
					FactoryDao.createPurchaseDao().update(purchase);
				}
			}
		} catch (ServiceException e) {
			throw e;
		}
	}

	@Override
	public void remove(Purchase purchase) throws ServiceException {
		FactoryDao.createPurchaseDao().remove(purchase);		
	}

	@Override
	public List<Purchase> list() throws ServiceException {
		return FactoryDao.createPurchaseDao().list(Purchase.class);
	}

	@Override
	public Purchase search(Integer id) throws ServiceException {
		return FactoryDao.createPurchaseDao().search(Purchase.class, id);
	}

}
