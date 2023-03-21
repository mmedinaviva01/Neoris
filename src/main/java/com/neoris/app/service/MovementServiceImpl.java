package com.neoris.app.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.neoris.app.entity.Account;
import com.neoris.app.entity.Movement;
import com.neoris.app.entity.Person;
import com.neoris.app.exception.MovementReportException;
import com.neoris.app.exception.ResourceNotFoundException;
import com.neoris.app.model.ReportModel;
import com.neoris.app.model.ResponseModel;
import com.neoris.app.repository.AccountRepository;
import com.neoris.app.repository.CustomerRepository;
import com.neoris.app.repository.MovementRepository;

@Service
public class MovementServiceImpl implements MovementService {

	@Autowired
	private MovementRepository movementRepository;

	@Autowired
	private AccountRepository accountRepository;

	@Autowired
	private CustomerRepository customerRepository;

	@Override
	@Transactional(readOnly = true)
	public ResponseModel findAll() {
		return ResponseModel.builder().message("Consulta exitosa").data(movementRepository.findAll()).build();
	}

	@Override
	@Transactional(readOnly = true)
	public ResponseModel findById(Long id) {
		return ResponseModel.builder().message("Consulta exitosa").data(movementRepository.findById(id)).build();
	}

	@Override
	@Transactional
	public ResponseModel save(Long id, Movement movement) {
		double balance;
		Optional<Account> account = accountRepository.findById(id);
		if (!account.isPresent())
			throw new ResourceNotFoundException("la cuenta no existe", HttpStatus.NOT_FOUND);
		if (!account.get().isState())
			throw new ResourceNotFoundException("la cuenta está bloqueada", HttpStatus.NOT_ACCEPTABLE);
		movement.setAccount(account.get());
		balance = operationBalance(movement, account.get());
		movement.setBalance(balance);
		movement.getAccount().setInitialBalance(balance);
		return ResponseModel.builder().message("Movimiento creado").data(movementRepository.save(movement)).build();
	}

	@Override
	@Transactional
	public ResponseModel getMovementDate(Date date, Long id) {
		Optional<Person> customer = customerRepository.findById(id);
		if (!customer.isPresent()) {
			throw new ResourceNotFoundException("El cliente no existe", HttpStatus.NOT_FOUND);
		}
		List<Movement> movements = movementRepository.getMovementByDate(id, date);
		if (movements.isEmpty())
			throw new ResourceNotFoundException("No existen movimientos del usuario " + customer.get().getName(),
					HttpStatus.NOT_FOUND);
		List<ReportModel> listModel = new ArrayList<>();
		for (Movement movement : movements) {
			double initialBalance;
			String movementType = String.valueOf(movement.getMovementType());
			if (movementType.equalsIgnoreCase("D"))
				initialBalance = movement.getBalance() + movement.getValue();
			else
				initialBalance = movement.getBalance() - movement.getValue();
			ReportModel reportModel = ReportModel.builder().cliente(movement.getAccount().getCustomer().getName())
					.fecha(movement.getDate()).numeroCuenta(movement.getAccount().getId())
					.tipo(movement.getAccount().getAccountType()).estado(movement.getAccount().isState())
					.saldoInicial(initialBalance).movimiento(movement.getValue()).saldoDisponible(movement.getBalance())
					.build();
			listModel.add(reportModel);
		}
		return ResponseModel.builder().message("Consulta exitosa").data(listModel).build();
	}

	@Override
	@Transactional
	public ResponseModel deleteById(Long id) {
		Movement movement = findMovement(id);
		movementRepository.deleteById(id);
		return ResponseModel.builder().message("Consulta exitosa").data(movement).build();
	}

	private double operationBalance(Movement movement, Account account) {
		double balance = account.getInitialBalance();
		String movementType = String.valueOf(movement.getMovementType());
		if (movementType.equalsIgnoreCase("D"))
			if (account.getInitialBalance() >= movement.getValue())
				balance = account.getInitialBalance() - movement.getValue();
			else
				throw new MovementReportException("Saldo insuficiente", HttpStatus.NOT_ACCEPTABLE);
		if (movementType.equalsIgnoreCase("C"))
			balance = account.getInitialBalance() + movement.getValue();
		if (!movementType.equalsIgnoreCase("D") && !movementType.equalsIgnoreCase("C"))
			throw new MovementReportException("El tipo de movimiento no es correcto", HttpStatus.NOT_ACCEPTABLE);
		return balance;
	}

	private Movement findMovement(Long id) {
		Optional<Movement> movement = movementRepository.findById(id);
		if (!movement.isPresent())
			throw new ResourceNotFoundException("El movimieto no existe", HttpStatus.NOT_FOUND);
		return movement.get();
	}
}