package com.subterrino.dao;

import java.util.List;

public interface Dao<T> {
	public void insert(T t);
	public void update(T t);
	public void remove(T t);
	public T search(Integer id);
	public List<T> list();
}
