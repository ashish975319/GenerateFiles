package com.file.upload.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.file.upload.controller.entity.Product;
import com.file.upload.repository.ProductRepository;
import com.file.upload.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;

	@Override
	public void saveFileData(Product product, byte[] fileByte) {
		
		

		productRepository.save(product);

	}

}
