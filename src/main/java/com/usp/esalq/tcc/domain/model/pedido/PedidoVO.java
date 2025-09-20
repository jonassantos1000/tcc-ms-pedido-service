package com.usp.esalq.tcc.domain.model.pedido;

import java.io.Serializable;
import java.math.BigDecimal;

public record PedidoVO (
		
		Long id,
	    String cliente,
	    String contato,
	    String email,
	    String endereco,
	    BigDecimal preco,
	    Integer quantidade,
	    BigDecimal valorTotal,
	    StatusPedido status,
	    Long produtoId,
	    Long usuarioId
		) implements Serializable{
}
