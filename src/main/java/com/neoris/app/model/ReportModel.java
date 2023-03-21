package com.neoris.app.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode()
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReportModel {
	private Date fecha;
	private String cliente;
	private Long numeroCuenta;
	private String tipo;
	private double saldoInicial;
	private boolean estado;
	private double movimiento;
	private double saldoDisponible;
}
