package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.domain.Order;
import com.example.repository.OrderRepository;

@Service
public class OrderService {

	@Autowired
	private OrderRepository orderRepository;

	public Order showOrderList(Integer userId, Integer status) {
		return orderRepository.findByUserIdAndStatus(userId, status);
	}

}
