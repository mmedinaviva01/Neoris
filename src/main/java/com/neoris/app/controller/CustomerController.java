package com.neoris.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.neoris.app.entity.Customer;
import com.neoris.app.model.ResponseModel;
import com.neoris.app.service.CustomerService;

@RestController
@RequestMapping("/api/clientes")
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	@GetMapping
	public ResponseEntity<ResponseModel> getAll() {
		return new ResponseEntity<>(customerService.findAll(), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<ResponseModel> create(@RequestBody Customer customer) {
		return new ResponseEntity<>(customerService.save(customer), HttpStatus.CREATED);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ResponseModel> getById(@PathVariable(value = "id") Long id) {
		return new ResponseEntity<>(customerService.findById(id), HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<ResponseModel> delete(@PathVariable(value = "id") Long id) {
		return new ResponseEntity<>(customerService.deleteById(id), HttpStatus.OK);
	}

	@PutMapping("/{id}")
	public ResponseEntity<ResponseModel> update(@PathVariable(value = "id") Long id, @RequestBody Customer customer) {
		customer.setId(id);
		return new ResponseEntity<>(customerService.save(customer), HttpStatus.OK);
	}

}
