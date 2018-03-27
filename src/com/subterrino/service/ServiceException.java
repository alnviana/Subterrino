package com.subterrino.service;

public class ServiceException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5965007683970149782L;

	public ServiceException(String error) {
		super(error);	
	}
	
}