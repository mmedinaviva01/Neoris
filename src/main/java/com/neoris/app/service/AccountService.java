package com.neoris.app.service;

import com.neoris.app.entity.Account;
import com.neoris.app.model.ResponseModel;

public interface AccountService {
	public ResponseModel findAll();
	public ResponseModel findById(Long id);
	public ResponseModel save(Long id, Account account);
	public ResponseModel deleteById(Long id);
	public ResponseModel update(Long id, Account account);
}
