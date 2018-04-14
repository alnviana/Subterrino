package com.subterrino.service;

import java.util.List;

import com.subterrino.dao.FactoryDao;
import com.subterrino.entity.Purchase;

public class PurchaseService implements GenericService<Purchase>{

	@Override
	public void save(Purchase purchase) throws ServiceException {
		try {
			if (purchase.getName() == null || purchase.getName().isEmpty()) {
				throw new ServiceException("A compra deve possuir o nome do comprador.");
			}
			
			if (purchase.getPhone() == null || purchase.getPhone().isEmpty()) {
				throw new ServiceException("A compra deve possuir um telefone.");
			}
			
			if (purchase.getAddress() == null || purchase.getAddress().isEmpty()) {
				throw new ServiceException("A compra deve possuir um endereço.");
			}
			
			if (!(purchase.getAdd_num() > 0)) {
				throw new ServiceException("A compra deve possuir o número do endereço.");
			}
			
			if (purchase.getPaymentType() == null) {
				throw new ServiceException("A compra deve possuir pelo menos um tipo de pagamento.");
			}
			
			if (!(purchase.getPurchaseItems().size() > 0)) {
				throw new ServiceException("A compra deve possuir pelo menos um produto.");
			}
			
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
