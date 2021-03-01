package com.educandoweb.course.services;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.educandoweb.course.dto.CategoryDTO;
import com.educandoweb.course.dto.ProductDTO;
import com.educandoweb.course.entities.Category;
import com.educandoweb.course.entities.Product;
import com.educandoweb.course.repositories.ProductRepository;

@Service
public class ProductService implements GenericService<Product, ProductDTO, Long> {

	@Autowired
	private ProductRepository repository;
	
	@Autowired
	private CategoryService categoryService;

	@Override
	public JpaRepository<Product, Long> getRepository() {
		return repository;
	}

	@Override
	public void updateData(Product entity, Product obj) {
		entity.setName(obj.getName());
		entity.setDescription(obj.getDescription());
		entity.setImgUrl(obj.getImgUrl());
		entity.setPrice(obj.getPrice());
	}

	@Override
	public ProductDTO insert(Product obj) {
		Set<CategoryDTO> categories = new HashSet<>();
		for (Category category : obj.getCategories()) {
			if (category.getId() == null) {
				categories.add(categoryService.insert(category));
			} else
				categories.add(categoryService.findById(category.getId()));
		}
		obj.getCategories().clear();
		for (CategoryDTO category : categories) {
			obj.getCategories().add(category.convert());
		}
		return repository.save(obj).convert();
	}
}
