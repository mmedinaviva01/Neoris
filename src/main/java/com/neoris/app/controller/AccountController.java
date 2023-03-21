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

import com.neoris.app.constants.Constants;
import com.neoris.app.entity.Account;
import com.neoris.app.model.ResponseModel;
import com.neoris.app.service.AccountService;

@RestController
@RequestMapping("/api")
public class AccountController {

	@Autowired
	private AccountService accountService;

	@GetMapping(Constants.END_POINT_ACCOUNT)
	public ResponseEntity<ResponseModel> getAll() {
		return new ResponseEntity<>(accountService.findAll(), HttpStatus.OK);
	}

	@PostMapping("/clientes/{id}" + Constants.END_POINT_ACCOUNT)
	public ResponseEntity<ResponseModel> create(@PathVariable(value = "id") Long id, @RequestBody Account account) {
		return new ResponseEntity<>(accountService.save(id, account), HttpStatus.CREATED);
	}

	@GetMapping(Constants.END_POINT_ACCOUNT + "/{id}")
	public ResponseEntity<ResponseModel> getById(@PathVariable(value = "id") Long id) {
		return new ResponseEntity<>(accountService.findById(id), HttpStatus.OK);
	}

	@DeleteMapping(Constants.END_POINT_ACCOUNT + "/{id}")
	public ResponseEntity<ResponseModel> delete(@PathVariable(value = "id") Long id) {
		return new ResponseEntity<>(accountService.deleteById(id), HttpStatus.OK);
	}

	@PutMapping(Constants.END_POINT_ACCOUNT + "/{id}")
	public ResponseEntity<ResponseModel> update(@PathVariable(value = "id") Long id, @RequestBody Account account) {
		return new ResponseEntity<>(accountService.update(id, account), HttpStatus.OK);
	}
}
