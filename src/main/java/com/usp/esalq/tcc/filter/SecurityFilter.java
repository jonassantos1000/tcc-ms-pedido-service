package com.usp.esalq.tcc.filter;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.usp.esalq.tcc.domain.entity.AutenticacaoEntity;
import com.usp.esalq.tcc.exceptions.ErrorResponseVO;
import com.usp.esalq.tcc.exceptions.ExceptionManager;
import com.usp.esalq.tcc.exceptions.MsgException;
import com.usp.esalq.tcc.repository.AutenticacaoRepository;
import com.usp.esalq.tcc.utils.SecurityContextUtils;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class SecurityFilter extends OncePerRequestFilter {
	
	private static final List<String> WHITE_LIST = List.of("/actuator/health",
															"/swagger",
															"/auth/autenticar",
															"/usuarios"
															);
	
	private final ObjectMapper objectMapper;
	private final ExceptionManager exceptionManager;
	private final AutenticacaoRepository autenticacaoRepository;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
		
		if (WHITE_LIST.contains(request.getRequestURI())) {
			filterChain.doFilter(request, response);
		} else {
			try {
				String token = request.getHeader(HttpHeaders.AUTHORIZATION);
				
				if (StringUtils.isBlank(token)) {
					throw new MsgException("Falha na Autenticação.", "Usuário não autorizado.");
				}
				token = token.replace("Bearer ", "");
				
				AutenticacaoEntity autenticacao = autenticacaoRepository.findByToken(UUID.fromString(token)).orElseThrow(() -> new MsgException("Token inválido."));
				SecurityContextUtils.setUsuario(autenticacao.getUsuario());
				filterChain.doFilter(request, response);
			} catch (MsgException e) {
				ResponseEntity<ErrorResponseVO> responseError = exceptionManager.tratarErro400(e, request);
				response.setStatus(responseError.getStatusCode().value());
				response.setContentType(MediaType.APPLICATION_JSON_VALUE);
				response.getWriter().write(objectMapper.writeValueAsString(responseError.getBody()));
			} finally {
				SecurityContextUtils.clear();
			}
		}
	}

}
