package com.usp.esalq.tcc.exceptions;

import org.springframework.validation.FieldError;

public record DetalheErroResponseVO(String mensagem, String detalhe) {
	public DetalheErroResponseVO(FieldError erro) { 
		this(erro.getField(), erro.getDefaultMessage());
	}
}
