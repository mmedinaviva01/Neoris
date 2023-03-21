package com.neoris.app.model;

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
public class ResponseModel {
	private String message;
	private Object data;
}
