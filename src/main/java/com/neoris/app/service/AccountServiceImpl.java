package com.neoris.app.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.neoris.app.entity.Account;
import com.neoris.app.entity.Customer;
import com.neoris.app.entity.Person;
import com.neoris.app.exception.ResourceNotFoundException;
import com.neoris.app.model.ResponseModel;
import com.neoris.app.repository.AccountRepository;
import com.neoris.app.repository.CustomerRepository;

@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
	private AccountRepository accountRepository;
	@Autowired
	private CustomerRepository customerRepository;

	@Override
	@Transactional(readOnly = true)
	public ResponseModel findAll() {
		return ResponseModel.builder().message("Consulta exitosa").data(accountRepository.findAll()).build();
	}

	@Override
	@Transactional(readOnly = true)
	public ResponseModel findById(Long id) {
		Account account = findAccount(id);
		return ResponseModel.builder().message("Consulta exitosa").data(account).build();
	}

	@Override
	@Transactional
	public ResponseModel save(Long id, Account account) {
		Optional<Person> customer = customerRepository.findById(id);
		if (!customer.isPresent())
			throw new ResourceNotFoundException("El usuario no existe", HttpStatus.NOT_FOUND);
		account.setCustomer((Customer) customer.get());
		return ResponseModel.builder().message("Cuenta creada").data(accountRepository.save(account)).build();
	}

	@Override
	@Transactional
	public ResponseModel update(Long id, Account account) {
		Account accountUpdate = findAccount(id);
		account.setId(accountUpdate.getId());
		account.setCustomer(accountUpdate.getCustomer());
		return ResponseModel.builder().message("Cuenta actualizada").data(accountRepository.save(account)).build();
	}

	@Override
	@Transactional
	public ResponseModel deleteById(Long id) {
		Account account = findAccount(id);
		accountRepository.deleteById(id);
		return ResponseModel.builder().message("Cuenta eliminada").data(account).build();
	}

	private Account findAccount(Long id) {
		Optional<Account> accountUpdate = accountRepository.findById(id);
		if (!accountUpdate.isPresent())
			throw new ResourceNotFoundException("la cuenta no existe", HttpStatus.NOT_FOUND);
		return accountUpdate.get();
	}
}