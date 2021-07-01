package com.example.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.Order;
import com.example.exception.ResourceNotFoundException;
import com.example.repository.OrderRepository;

@RestController
@RequestMapping("api/demo")
public class OrderController {

	@Autowired
	private OrderRepository orderRepository;

	@GetMapping("orders")
	public List<Order> getAllOrder() {
		return orderRepository.findAll();
	}

	@GetMapping("orders/{id}")
	public ResponseEntity<Order> getOrderById(@PathVariable(value = "id") Long orderId)
			throws ResourceNotFoundException {

		Order order = orderRepository.findById(orderId)
				.orElseThrow(() -> new ResourceNotFoundException("This order does not exit: " + orderId));

		return ResponseEntity.ok().body(order);
	}

	@PostMapping("orders")
	public Order createOrder(@Valid @RequestBody Order order) {
		return orderRepository.save(order);
	}

	@PutMapping("orders/{id}")
	public ResponseEntity<Order> updateOrder(@PathVariable(value = "id") Long orderId,
			@Valid @RequestBody Order orderDetails) throws ResourceNotFoundException {
		Order order = orderRepository.findById(orderId)
				.orElseThrow(() -> new ResourceNotFoundException("Order does not exist: " + orderId));

		order.setOrderName(orderDetails.getOrderName());
		order.setOrderQuantity(orderDetails.getOrderQuantity());
		order.setProductName(orderDetails.getProductName());
		order.setProductQuantity(orderDetails.getProductQuantity());

		final Order updatedOrder = orderRepository.save(order);

		return ResponseEntity.ok(updatedOrder);

	}

	@DeleteMapping("/orders/{id}")
	public Map<String, Boolean> deleteOrder(@PathVariable(value = "id") Long orderId) throws ResourceNotFoundException {

		Order order = orderRepository.findById(orderId)
				.orElseThrow(() -> new ResourceNotFoundException("This order does not exist : " + orderId));

		orderRepository.delete(order);

		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);

		return response;
	}
}
