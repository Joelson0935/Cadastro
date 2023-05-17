package com.casa.projeto.controladores;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.casa.projeto.dominio.dto.RequerimentoDeCadastro;
import com.casa.projeto.servico.ServicoCadastro;

@RestController
@RequestMapping("/cadastros")
public class ControladorCadastro {

	@Autowired
	private ServicoCadastro servico;

	@PostMapping("/guardar")
	public ResponseEntity<RequerimentoDeCadastro> guardarDados(
			@Valid @RequestBody RequerimentoDeCadastro requerimento) {
		return new ResponseEntity<RequerimentoDeCadastro>(servico.guardarDados(requerimento), HttpStatus.CREATED);
	}

	@PutMapping("/atualizar/{cadastroId}")
	public ResponseEntity<RequerimentoDeCadastro> atualizarDados(@Valid @PathVariable Long cadastroId,
			@RequestBody RequerimentoDeCadastro requerimento) {
		return ResponseEntity.ok(servico.atualizarDadosPorId(cadastroId, requerimento));
	}

	@GetMapping("/paginados")
	public ResponseEntity<Page<RequerimentoDeCadastro>> paginarDados(
			@PageableDefault(page = 0, size = 10, sort = "id", direction = Direction.ASC) Pageable pageable) {
		return ResponseEntity.ok(servico.buscarDadosPaginados(pageable));
	}

	@GetMapping("/listados")
	public ResponseEntity<List<RequerimentoDeCadastro>> buscarListaDeDados() {
		return ResponseEntity.ok(servico.buscarTodosOsDados());
	}

	@GetMapping("/nomes")
	public ResponseEntity<List<RequerimentoDeCadastro>> buscarDadosPorNome(@RequestParam(name = "nome") String nome) {
		return new ResponseEntity<List<RequerimentoDeCadastro>>(servico.buscarDadosPorNome(nome), HttpStatus.OK);
	}

	@GetMapping("/")
	public ResponseEntity<RequerimentoDeCadastro> buscarDadosPorId(@RequestParam(name = "id") Long id) {
		return ResponseEntity.ok(servico.buscarDadosPorId(id));
	}

	@DeleteMapping("/delete")
	public ResponseEntity<Void> deletarDadosPorId(@RequestParam(name = "deleteId") Long deleteId) {
		servico.deletarDadosPorId(deleteId);
		return ResponseEntity.noContent().build();
	}

}
