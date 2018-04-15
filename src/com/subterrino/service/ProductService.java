package com.subterrino.service;

import java.io.File;
import java.io.FileOutputStream;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.apache.catalina.core.ApplicationPart;

import com.subterrino.dao.FactoryDao;
import com.subterrino.entity.Product;

public class ProductService implements GenericService<Product> {

	@Override
	public void save(Product product) throws ServiceException {
		try {
			if (product.getName() == null || product.getName().isEmpty()) {
				throw new ServiceException("O produto deve possuir um nome.");
			}
			
			if (product.getDescription() == null || product.getDescription().isEmpty()) {
				throw new ServiceException("O produto deve possuir uma descrição.");
			}
			
			if (!(product.getPrice() > 0)) {
				throw new ServiceException("O produto deve possuir um preço.");
			}
			
			if (product.getColor() == null) {
				throw new ServiceException("O produto deve possuir uma cor.");
			}
			
			if (FactoryDao.createProductDao().search(Product.class, "name", product.getName()).get(0).getId() != product.getId()) {
				throw new ServiceException("Produto com este nome já cadastrado.");
			}
			
			if (product.getId() == null || product.getId().equals(0)) {
				product.setId(null);
				FactoryDao.createProductDao().insert(product);
			} else {
				Product p = FactoryDao.createProductDao().search(Product.class, product.getId());
				
				if (p == null) {
					throw new ServiceException("ID inexistente.");
				} else {
					ArrayList<String> old_photoList = p.getPhotoList();
					for(int i = 0; i < product.getPhotoList().size(); i++) {
						if (!product.getPhotoList().get(i).isEmpty()) {
							old_photoList.set(i, product.getPhotoList().get(i));
						}
					}
					product.setPhotoList(old_photoList);					
					
					FactoryDao.createProductDao().update(product);
				}
			}
		} catch (ServiceException e) {
			throw e;
		}		
	}

	@Override
	public void remove(Product product) throws ServiceException {
		FactoryDao.createProductDao().remove(product);		
	}

	@Override
	public List<Product> list() throws ServiceException {
		return FactoryDao.createProductDao().list(Product.class);
	}
	
	@Override
	public Product search(Integer id) throws ServiceException {
		return FactoryDao.createProductDao().search(Product.class, id);
	}
	
	public static ArrayList<String> SavePhotoList(ApplicationPart[] photoList) throws ServiceException {
		String imagesFolder;
		if (System.getProperty("os.name").toLowerCase().contains("windows")) {
			imagesFolder = "C:/temp/images/";
		} else {
			imagesFolder = "/subterrino_images/";
		}
		
		try {
			File f = new File(imagesFolder);
			if (f.exists()) {
				if (!f.isDirectory()) {
					try {
						f.delete();
						f.mkdirs();
					} catch (Exception e) {
						throw new ServiceException("Existe um arquivo no lugar do diretório de imagens. Não foi possível removê-lo.");
					}
				}
			} else {
				try {
					f.mkdirs();
				} catch (Exception e) {
					throw new ServiceException("O diretório de imagens não existe e não foi possível criá-lo.");
				}
			}
		} catch (ServiceException e) {
			throw e;
		}
		
		ArrayList<String> photo_path = new ArrayList<String>();		
		for(ApplicationPart photo : photoList) {
			try {
				if (photo != null && photo.getSize() > 0) {
					byte[] bytes = new byte[(int) photo.getSize()];
					photo.getInputStream().read(bytes);
					
					Timestamp timestamp = new Timestamp(System.currentTimeMillis());
					photo_path.add(imagesFolder + timestamp.getTime() + "-" + photo.getSubmittedFileName());
					File f = new File(photo_path.get(photo_path.size()-1));
					
					FileOutputStream fo = new FileOutputStream(f);
					fo.write(bytes);
					fo.close();
				} else {
					photo_path.add("");
				}
			} catch (Exception e) {
				photo_path.add("");
			}			
		}
		
		return photo_path;
	}

}
