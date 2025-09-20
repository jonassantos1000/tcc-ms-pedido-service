package com.usp.esalq.tcc.exceptions;

import lombok.Getter;

public class MsgException extends RuntimeException{
	
	@Getter
	private String mensagem;
	
	private static final long serialVersionUID = 1L;
	private static final String MENSAGEM_PADRAO = "Falha na requisição";
	
	public MsgException(String detalhe) {
		super(detalhe);
		this.mensagem = MENSAGEM_PADRAO;
	}
	
	public MsgException(String mensagem, String detalhe) {
		super(detalhe);
		this.mensagem = mensagem;
	}
}