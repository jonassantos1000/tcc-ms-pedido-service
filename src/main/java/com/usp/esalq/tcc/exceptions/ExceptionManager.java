package com.usp.esalq.tcc.exceptions;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@RestControllerAdvice
@RequiredArgsConstructor
public class ExceptionManager {

	@ExceptionHandler(MsgException.class)
	public ResponseEntity<ErrorResponseVO> tratarErro400(MsgException e, HttpServletRequest request){
		ErrorResponseVO erro = new ErrorResponseVO(Instant.now(), new DetalheErroResponseVO(e.getMensagem(), e.getMessage()), request.getRequestURI());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
	}
	
	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<ErrorResponseVO> tratarErroEntidadeInvalida(EntityNotFoundException e, HttpServletRequest request){
		String className = e.getMessage().split(" ")[3];
		className = className.substring(className.lastIndexOf(".") + 1).replace("Entity", "");
		String id =  e.getMessage().substring(e.getMessage().lastIndexOf(" ") + 1);
		ErrorResponseVO erro = new ErrorResponseVO(Instant.now(), new DetalheErroResponseVO("Recurso não encontrado", "%s com id %s não localizado.".formatted(className, id)), request.getRequestURI());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponseVO> tratarErroInesperado(Exception e, HttpServletRequest request){
		ErrorResponseVO erro = new ErrorResponseVO(Instant.now(), new DetalheErroResponseVO("Erro inesperado", e.getMessage()), request.getRequestURI());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
	}
}
