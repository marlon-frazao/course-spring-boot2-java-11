package com.educandoweb.course.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.educandoweb.course.dto.UserDTO;
import com.educandoweb.course.entities.User;
import com.educandoweb.course.repositories.UserRepository;

@Service
public class UserService implements GenericService<User, UserDTO, Long>{

	@Autowired
	private UserRepository repository;

	@Override
	public JpaRepository<User, Long> getRepository() {
		return repository;
	}

	@Override
	public void updateData(User entity, User obj) {
		entity.setName(obj.getName());
		entity.setEmail(obj.getEmail());
		entity.setPhone(obj.getPhone());
	}
}
