package com.usp.esalq.tcc.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.usp.esalq.tcc.domain.entity.AutenticacaoEntity;

@Repository
public interface AutenticacaoRepository extends JpaRepository<AutenticacaoEntity, Long> {

	@Query(" SELECT autenticacao FROM AutenticacaoEntity autenticacao "
			+ " JOIN FETCH autenticacao.usuario user "
			+ " WHERE autenticacao.token = :token ")
	Optional<AutenticacaoEntity> findByToken(UUID token);
}
