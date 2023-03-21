package com.neoris.app.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.neoris.app.entity.Person;
import com.neoris.app.exception.ResourceNotFoundException;
import com.neoris.app.model.ResponseModel;
import com.neoris.app.repository.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository customerRepository;

	@Override
	public ResponseModel findAll() {
		return ResponseModel.builder().message("Consulta exitosa").data(customerRepository.findAll()).build();
	}

	@Override
	public ResponseModel findById(Long id) {
		Person customer = findCustomer(id);
		return ResponseModel.builder().message("Consulta exitosa").data(customer).build();
	}

	@Override
	public ResponseModel save(Person customer) {
		if (customer.getId() != null)
			findCustomer(customer.getId());
		return ResponseModel.builder().message("Cliente creado").data(customerRepository.save(customer)).build();
	}

	@Override
	public ResponseModel deleteById(Long id) {
		Person customer = findCustomer(id);
		customerRepository.deleteById(id);
		return ResponseModel.builder().message("Cliente eliminado").data(customer).build();
	}

	private Person findCustomer(Long id) {
		Optional<Person> customer = customerRepository.findById(id);
		if (!customer.isPresent())
			throw new ResourceNotFoundException("El cliente no existe", HttpStatus.NOT_FOUND);
		return customer.get();
	}
}
