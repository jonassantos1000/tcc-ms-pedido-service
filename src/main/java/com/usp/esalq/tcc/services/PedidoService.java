package com.usp.esalq.tcc.services;

import java.math.BigDecimal;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.usp.esalq.tcc.domain.entity.PedidoEntity;
import com.usp.esalq.tcc.domain.mapper.PedidoMapper;
import com.usp.esalq.tcc.domain.model.pedido.DadosPedidoVO;
import com.usp.esalq.tcc.domain.model.pedido.PedidoFaturadoVO;
import com.usp.esalq.tcc.domain.model.pedido.PedidoVO;
import com.usp.esalq.tcc.domain.model.pedido.StatusPedido;
import com.usp.esalq.tcc.repository.PedidoRepository;
import com.usp.esalq.tcc.repository.ProdutoRepository;
import com.usp.esalq.tcc.utils.SecurityContextUtils;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PedidoService {
	
	private final PedidoMapper mapper;

	private final ProdutoRepository produtoRepository;
	private final PedidoRepository pedidoRepository;
	

	@CacheEvict(value = "pedidos", allEntries = true)
	@Transactional
	public PedidoFaturadoVO faturar(DadosPedidoVO dadosPedidoVO) {
		PedidoEntity entity = mapper.toPedidoEntity(dadosPedidoVO, produtoRepository.getReferenceById(dadosPedidoVO.produtoId()), SecurityContextUtils.getUsuario());
		entity.setValorTotal(dadosPedidoVO.preco().multiply(BigDecimal.valueOf(dadosPedidoVO.quantidade())));
		return mapper.toPedidoFaturadoVO(pedidoRepository.save(entity));
	}

	@Cacheable("pedidos")
	@Transactional(readOnly = true) 
	public Page<PedidoVO> listar(Pageable page) {
		return pedidoRepository.findAll(page).map(mapper::toPedidoVO);
	}
	
	@Transactional(readOnly = true) 
	protected PedidoEntity getById(Long pedidoId) {
		return pedidoRepository.getReferenceById(pedidoId);
	}
	
	@Transactional
	protected void confirmarPagamento(PedidoEntity pedido) {
		pedido.setStatus(StatusPedido.PAGAMENTO_EFETUADO);
		pedidoRepository.save(pedido);
	}
}
