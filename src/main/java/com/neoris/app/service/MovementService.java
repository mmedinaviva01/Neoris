package com.neoris.app.service;

import java.util.Date;

import com.neoris.app.entity.Movement;
import com.neoris.app.model.ResponseModel;

public interface MovementService {
	public ResponseModel findAll();
	public ResponseModel findById(Long id);
	public ResponseModel save(Long id, Movement movement);
	public ResponseModel deleteById(Long id);
	public ResponseModel getMovementDate(Date date, Long id);
}
