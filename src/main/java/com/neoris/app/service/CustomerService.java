package com.neoris.app.service;

import com.neoris.app.entity.Person;
import com.neoris.app.model.ResponseModel;

public interface CustomerService {
	public ResponseModel findAll();
	public ResponseModel findById(Long id);
	public ResponseModel save(Person account);
	public ResponseModel deleteById(Long id);
}
