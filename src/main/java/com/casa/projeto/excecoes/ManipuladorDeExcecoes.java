package com.casa.projeto.excecoes;

import java.time.LocalDate;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ManipuladorDeExcecoes {

	@ExceptionHandler(ObjetoNaoEncontrado.class)
	public ResponseEntity<Erro> objectoNaoEncontrado(ObjetoNaoEncontrado e, HttpServletRequest request) {
		Erro erro = new Erro(e.getMessage(), HttpStatus.NOT_FOUND.value(), LocalDate.now(), request.getRequestURI());
		return new ResponseEntity<Erro>(erro, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Erro> argumentoNaoValido(MethodArgumentNotValidException e, HttpServletRequest request) {
		Erro erro = new Erro("Argumento não validado", HttpStatus.BAD_REQUEST.value(), LocalDate.now(),
				request.getRequestURI());
		for (FieldError field : e.getBindingResult().getFieldErrors()) {
			erro.setCampos(field.getField(), field.getDefaultMessage());
		}
		return new ResponseEntity<Erro>(erro, HttpStatus.BAD_REQUEST);
	}

}
