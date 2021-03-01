package com.educandoweb.course.dto;

import java.util.HashSet;
import java.util.Set;

import com.educandoweb.course.entities.Category;
import com.educandoweb.course.util.Convertible;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class CategoryDTO implements Convertible<Category>{

	private Long id;
	private String name;
	
	@JsonIgnore
	private Set<ProductDTO> products = new HashSet<>();

	public CategoryDTO() {
	}

	public CategoryDTO(Long id, String name) {
		this.id = id;
		this.name = name;
	}

	public CategoryDTO(Category entity) {
		this.id = entity.getId();
		this.name = entity.getName();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@JsonIgnore
	public Set<ProductDTO> getProducts() {
		return products;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CategoryDTO other = (CategoryDTO) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	
	@Override
	public String toString() {
		return "CategoryDTO [id=" + id + ", name=" + name + "]";
	}

	@Override
	public Category convert() {
		return new Category(this);
	}

	

}
