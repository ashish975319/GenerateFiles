package com.file.upload.service;

import com.file.upload.controller.entity.Product;

public interface ProductService {

	void saveFileData(Product product, byte[] fileByte);

}
