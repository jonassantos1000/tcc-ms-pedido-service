package com.usp.esalq.tcc.domain.mapper;

import com.usp.esalq.tcc.domain.entity.PedidoEntity;
import com.usp.esalq.tcc.domain.entity.ProdutoEntity;
import com.usp.esalq.tcc.domain.entity.UsuarioEntity;
import com.usp.esalq.tcc.domain.model.pedido.DadosPedidoVO;
import com.usp.esalq.tcc.domain.model.pedido.PedidoFaturadoVO;
import com.usp.esalq.tcc.domain.model.pedido.PedidoVO;
import com.usp.esalq.tcc.domain.model.pedido.StatusPedido;
import java.math.BigDecimal;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-09-20T13:08:39-0300",
    comments = "version: 1.5.3.Final, compiler: Eclipse JDT (IDE) 3.39.0.v20240820-0604, environment: Java 21.0.4 (Eclipse Adoptium)"
)
@Component
public class PedidoMapperImpl implements PedidoMapper {

    @Override
    public PedidoEntity toPedidoEntity(DadosPedidoVO dadosPedidoVO, ProdutoEntity produto, UsuarioEntity usuario) {
        if ( dadosPedidoVO == null && produto == null && usuario == null ) {
            return null;
        }

        PedidoEntity pedidoEntity = new PedidoEntity();

        if ( dadosPedidoVO != null ) {
            pedidoEntity.setEmail( dadosPedidoVO.email() );
            pedidoEntity.setPreco( dadosPedidoVO.preco() );
            pedidoEntity.setCliente( dadosPedidoVO.cliente() );
            pedidoEntity.setContato( dadosPedidoVO.contato() );
            pedidoEntity.setEndereco( dadosPedidoVO.endereco() );
            pedidoEntity.setQuantidade( dadosPedidoVO.quantidade() );
        }
        if ( produto != null ) {
            pedidoEntity.setProduto( produto );
            pedidoEntity.setUsuario( produto.getUsuario() );
        }
        pedidoEntity.setStatus( com.usp.esalq.tcc.domain.model.pedido.StatusPedido.PENDENTE_PAGAMENTO );

        return pedidoEntity;
    }

    @Override
    public PedidoVO toPedidoVO(PedidoEntity entity) {
        if ( entity == null ) {
            return null;
        }

        Long produtoId = null;
        Long usuarioId = null;
        Long id = null;
        String cliente = null;
        String contato = null;
        String email = null;
        String endereco = null;
        BigDecimal preco = null;
        Integer quantidade = null;
        BigDecimal valorTotal = null;
        StatusPedido status = null;

        produtoId = entityProdutoId( entity );
        usuarioId = entityUsuarioId( entity );
        id = entity.getId();
        cliente = entity.getCliente();
        contato = entity.getContato();
        email = entity.getEmail();
        endereco = entity.getEndereco();
        preco = entity.getPreco();
        quantidade = entity.getQuantidade();
        valorTotal = entity.getValorTotal();
        status = entity.getStatus();

        PedidoVO pedidoVO = new PedidoVO( id, cliente, contato, email, endereco, preco, quantidade, valorTotal, status, produtoId, usuarioId );

        return pedidoVO;
    }

    @Override
    public PedidoFaturadoVO toPedidoFaturadoVO(PedidoEntity entity) {
        if ( entity == null ) {
            return null;
        }

        Long id = null;

        id = entity.getId();

        PedidoFaturadoVO pedidoFaturadoVO = new PedidoFaturadoVO( id );

        return pedidoFaturadoVO;
    }

    private Long entityProdutoId(PedidoEntity pedidoEntity) {
        if ( pedidoEntity == null ) {
            return null;
        }
        ProdutoEntity produto = pedidoEntity.getProduto();
        if ( produto == null ) {
            return null;
        }
        Long id = produto.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Long entityUsuarioId(PedidoEntity pedidoEntity) {
        if ( pedidoEntity == null ) {
            return null;
        }
        UsuarioEntity usuario = pedidoEntity.getUsuario();
        if ( usuario == null ) {
            return null;
        }
        Long id = usuario.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
