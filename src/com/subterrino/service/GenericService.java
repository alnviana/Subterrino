package com.subterrino.service;

import java.util.List;

public interface GenericService<T> {
	public void save(T t) throws ServiceException;
	public void remove(T t) throws ServiceException;
	public List<T> list() throws ServiceException;
	public T search(Integer id) throws ServiceException;
}
