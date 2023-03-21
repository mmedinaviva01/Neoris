package com.neoris.app.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.neoris.app.constants.Constants;
import com.neoris.app.entity.Movement;
import com.neoris.app.model.ResponseModel;
import com.neoris.app.service.MovementService;

@RestController
@RequestMapping("/api")
public class MovementController {

	@Autowired
	private MovementService movementService;

	@GetMapping(Constants.END_POINT_MOVEMENT)
	public ResponseEntity<ResponseModel> getAll() {
		return new ResponseEntity<>(movementService.findAll(), HttpStatus.OK);
	}

	@PostMapping(value = "cuentas/{id}" + Constants.END_POINT_MOVEMENT, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseModel> create(@PathVariable(value = "id") Long id, @RequestBody Movement movement) {
		return new ResponseEntity<>(movementService.save(id, movement), HttpStatus.CREATED);
	}

	@GetMapping(value = Constants.END_POINT_MOVEMENT + "/{id}")
	public ResponseEntity<ResponseModel> getById(@PathVariable(value = "id") Long id) {
		return new ResponseEntity<>(movementService.findById(id), HttpStatus.OK);
	}

	@DeleteMapping(Constants.END_POINT_MOVEMENT + "/{id}")
	public ResponseEntity<ResponseModel> delete(@PathVariable(value = "id") Long id) {
		return new ResponseEntity<>(movementService.deleteById(id), HttpStatus.OK);
	}

	@GetMapping(value = Constants.END_POINT_MOVEMENT
			+ "/clientes/{id}/reportes", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseModel> getMovementByDate(@PathVariable(value = "id") Long id,
			@RequestParam("date") String date) {
		Date myDate = null;
		try {
			myDate = new SimpleDateFormat("yyyy-MM-dd").parse(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResponseEntity<>(movementService.getMovementDate(myDate, id), HttpStatus.OK);
	}
}
