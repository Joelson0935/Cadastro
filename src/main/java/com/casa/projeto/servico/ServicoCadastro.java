package com.casa.projeto.servico;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.casa.projeto.dominio.dto.RequerimentoDeCadastro;

public interface ServicoCadastro {

	RequerimentoDeCadastro guardarDados(RequerimentoDeCadastro requerimento);

	Page<RequerimentoDeCadastro> buscarDadosPaginados(Pageable pageable);

	List<RequerimentoDeCadastro> buscarTodosOsDados();

	RequerimentoDeCadastro buscarDadosPorId(Long id);

	List<RequerimentoDeCadastro> buscarDadosPorNome(String nome);

	RequerimentoDeCadastro atualizarDadosPorId(Long id, RequerimentoDeCadastro requerimento);

	void deletarDadosPorId(Long id);

}
