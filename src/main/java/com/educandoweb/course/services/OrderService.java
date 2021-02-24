package com.educandoweb.course.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.educandoweb.course.dto.OrderDTO;
import com.educandoweb.course.entities.Order;
import com.educandoweb.course.repositories.OrderRepository;

@Service
public class OrderService implements GenericService<Order, OrderDTO, Long>{
	@Autowired
	private OrderRepository repository;

	@Override
	public JpaRepository<Order, Long> getRepository() {
		return repository;
	}
}
