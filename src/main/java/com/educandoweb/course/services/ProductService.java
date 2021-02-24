package com.educandoweb.course.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.educandoweb.course.dto.ProductDTO;
import com.educandoweb.course.entities.Product;
import com.educandoweb.course.repositories.ProductRepository;

@Service
public class ProductService implements GenericService<Product, ProductDTO, Long>{

	@Autowired
	private ProductRepository repository;
	
	@Override
	public JpaRepository<Product, Long> getRepository() {
		return repository;
	}

}
