package com.casa.projeto.excecoes;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Campos implements Serializable {
	private static final long serialVersionUID = 1L;

	private String fieldName;
	private String fieldError;

}
