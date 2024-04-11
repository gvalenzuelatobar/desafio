package com.neoris.usuarios.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PhoneRequest {
	private Integer number;
	private Integer citycode;
	private Integer contrycode;
}
