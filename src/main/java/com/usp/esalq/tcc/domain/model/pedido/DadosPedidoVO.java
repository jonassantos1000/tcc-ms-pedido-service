package com.usp.esalq.tcc.domain.model.pedido;

import java.math.BigDecimal;

import lombok.Builder;

@Builder
public record DadosPedidoVO(
		String cliente,
		String contato,
		String email,
		String endereco,
		Integer quantidade,
		BigDecimal preco,
		Long produtoId
		) {
}
