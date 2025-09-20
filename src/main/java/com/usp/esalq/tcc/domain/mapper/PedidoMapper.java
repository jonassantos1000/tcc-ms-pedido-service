package com.usp.esalq.tcc.domain.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.usp.esalq.tcc.domain.entity.PedidoEntity;
import com.usp.esalq.tcc.domain.entity.ProdutoEntity;
import com.usp.esalq.tcc.domain.entity.UsuarioEntity;
import com.usp.esalq.tcc.domain.model.pedido.DadosPedidoVO;
import com.usp.esalq.tcc.domain.model.pedido.PedidoFaturadoVO;
import com.usp.esalq.tcc.domain.model.pedido.PedidoVO;

@Mapper(componentModel = "spring")
public interface PedidoMapper {
	
	@Mapping(target = "id", ignore = true)
	@Mapping(target = "email", source = "dadosPedidoVO.email")
	@Mapping(target = "preco", source = "dadosPedidoVO.preco")
	@Mapping(target = "produto", source = "produto")
	@Mapping(target = "status", expression = "java(com.usp.esalq.tcc.domain.model.pedido.StatusPedido.PENDENTE_PAGAMENTO)")
	PedidoEntity toPedidoEntity(DadosPedidoVO dadosPedidoVO, ProdutoEntity produto, UsuarioEntity usuario);
	
	@Mapping(target = "produtoId", source = "produto.id")
	@Mapping(target = "usuarioId", source = "usuario.id")
	PedidoVO toPedidoVO(PedidoEntity entity);

	PedidoFaturadoVO toPedidoFaturadoVO(PedidoEntity entity);
}
