package com.subterrino.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.subterrino.dao.FactoryDao;
import com.subterrino.entity.Product;
import com.subterrino.service.ProductService;

@WebServlet("/ServletProductImage")
public class ServletProductImage extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public ServletProductImage() {
        super();
    }

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
		String id = req.getParameter("id");
		Integer image = Integer.parseInt(req.getParameter("image"));
		
		String projectRootPath = getServletContext().getRealPath(File.separator);
		try {
			Product p = new ProductService().search(new Integer(id));    	
	    	
	    	ArrayList<String> photoList = p.getPhotoList();
	    	if (image >= 0 && image <= photoList.size()-1) {
	    		byte[] file;
	    				
	    		try {
					file = ReadFile(photoList.get(image));
				} catch (Exception e) {
					file = ReadFile(projectRootPath + "images" + File.separator + "nophoto.jpg");
				}
	        	
	        	resp.getOutputStream().write(file);
	    	} 
		} catch (Exception e) {
			System.out.println("Não foi possível carregar o produto.");
		}		   	
	}
	
	private byte[] ReadFile(String path) throws IOException {
		File f = new File(path);
    	byte[] file = new byte[(int) f.length()];
    	FileInputStream fi = new FileInputStream(f);
    	
    	fi.read(file);
    	fi.close();
    	
    	return file;
	}
}
