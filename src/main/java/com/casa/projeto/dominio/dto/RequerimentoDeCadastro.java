package com.casa.projeto.dominio.dto;

import java.io.Serializable;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RequerimentoDeCadastro implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	@NotBlank(message = "branco ou nulo não são aceitos")
	@Size(min = 4, message = "mínimo de 3 caracteres")
	private String nome;
	@Min(value = 1, message = "Idade inválida")
	@Max(value = 130, message = "Idade inválida")
	private Integer idade;
	@NotBlank(message = "branco ou nulo não são aceitos")
	@Size(min = 8, message = "mínimo de 8 caracteres")
	@JsonProperty(access = Access.WRITE_ONLY)
	private String senha;
}