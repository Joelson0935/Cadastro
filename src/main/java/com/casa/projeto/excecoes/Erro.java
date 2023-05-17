package com.casa.projeto.excecoes;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class Erro implements Serializable {
	private static final long serialVersionUID = 1L;

	private String error;
	private Integer codigo;
	private LocalDate data;
	private String path;
	private List<Campos> campos = new ArrayList<>();

	public void setCampos(String nome, String erro) {
		campos.add(new Campos(nome, erro));
	}

	public Erro(String error, Integer codigo, LocalDate data, String path) {
		super();
		this.error = error;
		this.codigo = codigo;
		this.data = data;
		this.path = path;
	}
}
