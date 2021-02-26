package com.educandoweb.course.dto;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

import com.educandoweb.course.entities.Order;
import com.educandoweb.course.entities.OrderItem;
import com.educandoweb.course.entities.Payment;
import com.educandoweb.course.entities.enums.OrderStatus;
import com.fasterxml.jackson.annotation.JsonFormat;

public class OrderDTO {

	private Long id;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
	private Instant moment;
	private OrderStatus orderStatus;
	private UserDTO client;
	
	private Set<OrderItem> items = new HashSet<>();
	
	private Payment payment;
	private Double total;

	public OrderDTO() {
	}

	public OrderDTO(Long id, Instant moment, OrderStatus orderStatus, UserDTO client) {
		this.id = id;
		this.moment = moment;
		this.orderStatus = orderStatus;
		this.client = client;
	}

	public OrderDTO(Order entity) {
		this.id = entity.getId();
		this.moment = entity.getMoment();
		this.orderStatus = entity.getOrderStatus();
		this.client = entity.getClient().convert();
		this.items = entity.getItems();
		this.total = entity.total();
		this.payment = entity.getPayment();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Instant getMoment() {
		return moment;
	}

	public void setMoment(Instant moment) {
		this.moment = moment;
	}
	
	public OrderStatus getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
	}
	public UserDTO getClient() {
		return client;
	}

	public void setClient(UserDTO client) {
		this.client = client;
	}
	
	public Set<OrderItem> getItems() {
		return items;
	}
	
	public Payment getPayment() {
		return payment;
	}
	
	public Double getTotal() {
		return total;
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
		OrderDTO other = (OrderDTO) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
