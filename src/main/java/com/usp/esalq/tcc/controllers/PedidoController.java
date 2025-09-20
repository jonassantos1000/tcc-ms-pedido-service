package com.usp.esalq.tcc.controllers;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.usp.esalq.tcc.domain.model.pedido.DadosPedidoVO;
import com.usp.esalq.tcc.domain.model.pedido.PedidoFaturadoVO;
import com.usp.esalq.tcc.domain.model.pedido.PedidoVO;
import com.usp.esalq.tcc.services.PedidoService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/pedidos")
@RequiredArgsConstructor
public class PedidoController {

	private final PedidoService pedidoService;
	
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	private PedidoFaturadoVO cadastrar(@RequestBody DadosPedidoVO dadosPedidoVO) {
		return pedidoService.faturar(dadosPedidoVO);
	}
	
	@GetMapping
	private Page<PedidoVO> listar(@PageableDefault(size = 20) Pageable page) {
		return pedidoService.listar(page);
	}
}
