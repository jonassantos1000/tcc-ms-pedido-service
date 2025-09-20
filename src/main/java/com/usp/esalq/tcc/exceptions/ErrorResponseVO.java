package com.usp.esalq.tcc.exceptions;

import java.time.Instant;
import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

public record ErrorResponseVO (
		@JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd'T'HH:mm:ss'Z'", timezone="GMT")
		Instant timestamp,
		
		List<DetalheErroResponseVO> erros,
		
		String path) {
	
	public ErrorResponseVO(Instant timestamp, DetalheErroResponseVO erros, String path) {
		this(timestamp, Arrays.asList(erros), path);
	}
}