package com.example.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="orders")
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(name="order_name", nullable = false)
	private String orderName;
	
	@Column(name="order_quantity", nullable = false)
	private int orderQuantity;
	
	@Column(name="product_name", nullable = false)
	private String productName;
	
	@Column(name="product_quantity", nullable = false)
	private int productQuantity;
	
}
