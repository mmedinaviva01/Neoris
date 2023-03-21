package com.neoris.app.entity;

import java.io.Serializable;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "account")
@NoArgsConstructor
@Getter
@Setter
public class Account implements Serializable{
	
	private static final long serialVersionUID = 5115701149568019518L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(nullable = false)
	private String accountType;
	@Column(nullable = false)
	private double initialBalance;
	@Column(nullable = false)
	private boolean state;
	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "idCustomer", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Customer customer;
	
}
