package com.neoris.app.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "customer")
public class Customer extends Person{
	@Column(nullable = false)
	private int password;
	@Column(nullable = false)
	private boolean state;
	
	@Builder
	public Customer(Long id, String name, String gender, int age, String identification, String address,
			String phone, int password, boolean state) {
		super(id, name, gender, age, identification, address, phone);
		this.password = password;
		this.state = state;
	}
	
	
}
