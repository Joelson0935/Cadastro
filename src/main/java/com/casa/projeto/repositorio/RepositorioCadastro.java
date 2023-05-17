package com.casa.projeto.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.casa.projeto.dominio.Cadastro;

@Repository
public interface RepositorioCadastro extends JpaRepository<Cadastro, Long> {

	@Query("select c from Cadastro c where c.nome like %?1%")
	List<Cadastro> buscarPorNome(String nome);
}
