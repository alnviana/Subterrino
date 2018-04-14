package com.subterrino.service;

import java.util.List;

import com.subterrino.dao.FactoryDao;
import com.subterrino.entity.Color;
import com.subterrino.entity.PaymentType;

public class PaymentTypeService implements GenericService<PaymentType>{

	@Override
	public void save(PaymentType paymentType) throws ServiceException {
		if ((paymentType.getId() == null || paymentType.getId().equals(0)) && (paymentType.getName() == null || paymentType.getName() == "")) {
			throw new ServiceException("O tipo de pagamento não pode ser nulo ou vazio.");
		}
		
		if (paymentType.getName().length() > 20) {
			throw new ServiceException("O tipo de pagamento não pode ser maior que 20 caracteres.");
		}
		
		if (! FactoryDao.createPaymentTypeDao().search(PaymentType.class, "name", paymentType.getName()).isEmpty()) {
			throw new ServiceException("Tipo de pagamento já cadastrado.");
		}
		
		try {
			if (paymentType.getId() == null || paymentType.getId().equals(0)) {
				paymentType.setId(null);
				FactoryDao.createPaymentTypeDao().insert(paymentType);
			} else {
				PaymentType pt = FactoryDao.createPaymentTypeDao().search(PaymentType.class, paymentType.getId());
				
				if (pt == null) {
					throw new ServiceException("ID inexistente.");
				} else {
					FactoryDao.createPaymentTypeDao().update(paymentType);
				}
			}
		} catch (ServiceException e) {
			throw e;
		}
	}

	@Override
	public void remove(PaymentType t) throws ServiceException {
		FactoryDao.createPaymentTypeDao().remove(t);		
	}

	@Override
	public List<PaymentType> list() throws ServiceException {
		return FactoryDao.createPaymentTypeDao().list(PaymentType.class);
	}

	@Override
	public PaymentType search(Integer id) throws ServiceException {
		return FactoryDao.createPaymentTypeDao().search(PaymentType.class, id);
	}
}
