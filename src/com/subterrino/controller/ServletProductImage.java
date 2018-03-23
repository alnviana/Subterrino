package com.subterrino.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.subterrino.dao.FactoryDao;
import com.subterrino.entity.Product;

@WebServlet("/ServletProductImage")
public class ServletProductImage extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public ServletProductImage() {
        super();
    }

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("id");
		Integer image = Integer.parseInt(req.getParameter("image"));
    	Product p = FactoryDao.createProductDao().search(Product.class, new Integer(id));
    	
    	ArrayList<String> photoList = p.getPhotoList();
    	if (image >= 0 && image <= photoList.size()-1) {
    		File f = new File(photoList.get(image));
        	byte[] file = new byte[(int) f.length()];
        	FileInputStream fi = new FileInputStream(f);
        	
        	fi.read(file);
        	fi.close();
        	
        	resp.getOutputStream().write(file);
    	}
    	
	}
}
