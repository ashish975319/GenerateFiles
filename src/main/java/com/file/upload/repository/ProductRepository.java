package com.file.upload.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.file.upload.controller.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
	

}
