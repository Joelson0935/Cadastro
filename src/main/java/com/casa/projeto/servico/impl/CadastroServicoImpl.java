package com.casa.projeto.servico.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.casa.projeto.dominio.Cadastro;
import com.casa.projeto.dominio.dto.RequerimentoDeCadastro;
import com.casa.projeto.excecoes.ObjetoNaoEncontrado;
import com.casa.projeto.repositorio.RepositorioCadastro;
import com.casa.projeto.servico.ServicoCadastro;

@Service
public class CadastroServicoImpl implements ServicoCadastro {

	@Autowired
	private RepositorioCadastro repositorio;

	// Métodos Privados

	private RequerimentoDeCadastro entidadeParaDto(Cadastro cadastro) {
		RequerimentoDeCadastro dto = new RequerimentoDeCadastro(cadastro.getId(), cadastro.getNome(),
				cadastro.getIdade(), cadastro.getSenha());
		return dto;
	}

	private Cadastro dtoParaEntidade(RequerimentoDeCadastro requerimento) {
		Cadastro cadastro = new Cadastro(requerimento.getId(), requerimento.getNome(), requerimento.getIdade(),
				requerimento.getSenha());
		return cadastro;
	}

	private Cadastro buscarPorId(Long id) {
		Cadastro cadastro = repositorio.findById(id)
				.orElseThrow(() -> new ObjetoNaoEncontrado("Cadastro não encontrado."));
		return cadastro;
	}

	// Métodos públicos

	@Override
	public RequerimentoDeCadastro guardarDados(RequerimentoDeCadastro requerimento) {
		Cadastro cadastro = dtoParaEntidade(requerimento);
		if (cadastro != null) {
			repositorio.save(cadastro);
		}
		RequerimentoDeCadastro novoRequerimento = entidadeParaDto(cadastro);
		return novoRequerimento;
	}

	@Override
	public Page<RequerimentoDeCadastro> buscarDadosPaginados(Pageable pageable) {
		Page<Cadastro> cadastros = repositorio.findAll(pageable);
		Page<RequerimentoDeCadastro> requerimentos = cadastros.map(cadastro -> entidadeParaDto(cadastro));
		return requerimentos;
	}

	@Override
	public List<RequerimentoDeCadastro> buscarTodosOsDados() {
		List<Cadastro> cadastros = repositorio.findAll();
		List<RequerimentoDeCadastro> requerimentos = cadastros.stream().map(cadastro -> entidadeParaDto(cadastro))
				.collect(Collectors.toList());
		return requerimentos;
	}

	@Override
	public RequerimentoDeCadastro buscarDadosPorId(Long id) {
		Cadastro cadastro = repositorio.findById(id)
				.orElseThrow(() -> new ObjetoNaoEncontrado("Cadastro não encontrado."));
		RequerimentoDeCadastro requerimento = entidadeParaDto(cadastro);
		return requerimento;
	}

	@Override
	public List<RequerimentoDeCadastro> buscarDadosPorNome(String nome) {
		List<Cadastro> cadastros = repositorio.buscarPorNome(nome);
		if (cadastros == null) {
			throw new ObjetoNaoEncontrado("Nome não encontrado ");
		}
		List<RequerimentoDeCadastro> requerimentos = cadastros.stream().map(cadastro -> entidadeParaDto(cadastro))
				.collect(Collectors.toList());
		return requerimentos;
	}

	@Override
	public RequerimentoDeCadastro atualizarDadosPorId(Long id, RequerimentoDeCadastro requerimento) {
		Cadastro cadastroEncontrado = buscarPorId(id);
		cadastroEncontrado = dtoParaEntidade(requerimento);
		cadastroEncontrado.setId(id);
		requerimento = entidadeParaDto(cadastroEncontrado);
		return guardarDados(requerimento);
	}

	@Override
	public void deletarDadosPorId(Long id) {
		Cadastro cadastroEncontrado = buscarPorId(id);
		repositorio.deleteById(cadastroEncontrado.getId());
	}

}
