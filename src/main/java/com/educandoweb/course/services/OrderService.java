package com.educandoweb.course.services;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.educandoweb.course.dto.OrderDTO;
import com.educandoweb.course.entities.Order;
import com.educandoweb.course.entities.OrderItem;
import com.educandoweb.course.entities.Payment;
import com.educandoweb.course.entities.Product;
import com.educandoweb.course.repositories.OrderItemRepository;
import com.educandoweb.course.repositories.OrderRepository;
import com.educandoweb.course.repositories.ProductRepository;
import com.educandoweb.course.repositories.UserRepository;

@Service
public class OrderService implements GenericService<Order, OrderDTO, Long>{
	@Autowired
	private OrderRepository repository;
	
	@Autowired
	private OrderItemRepository orderItemRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public JpaRepository<Order, Long> getRepository() {
		return repository;
	}

	@Override
	public void updateData(Order entity, Order obj) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public OrderDTO insert(Order obj) {
		Order order = new Order(null, obj.getMoment(), obj.getOrderStatus(), userRepository.getOne(obj.getClient().getId()));
		order = repository.save(order);
		
		Set<OrderItem> items = new HashSet<>();
		
		for(OrderItem i : obj.getItems()) {
			Product product = productRepository.getOne(i.getProduct().getId());
			items.add(new OrderItem(order, product , i.getQuantity(), product.getPrice()));
		}
		
		orderItemRepository.saveAll(items);
		
		if(obj.getPayment().getMoment() != null) {
			order.setPayment(new Payment(null, obj.getPayment().getMoment(), order));
			return repository.save(order).convert();
		}
		
		return order.convert();
	}
}
