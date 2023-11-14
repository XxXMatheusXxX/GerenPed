package com.yay.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yay.entities.Pedidos;
import com.yay.services.PedidosService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "Pedidos", description = "API REST DE GERENCIAMENTO DO Pedidos")
@RestController
@RequestMapping("/pedidos")

public class PedidosController {

	private final PedidosService PedidosService;

	@Autowired
	public PedidosController(PedidosService PedidosService) {
		this.PedidosService = PedidosService;
	}

	@GetMapping("/{id}")
	@Operation(summary = "Localiza Pedidos por ID")
	public ResponseEntity<Pedidos> buscaPedidosControlId(@PathVariable Long id) {
		Pedidos Pedidos = PedidosService.getPedidosById(id);
		if (Pedidos != null) {
			return ResponseEntity.ok(Pedidos);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping
	@Operation(summary = "apresenta todos os Pedidoss")
	public ResponseEntity<List<Pedidos>> buscaTodasLigacoesControl() {
		List<Pedidos> Pedidos = PedidosService.getAllPedidoss();
		return ResponseEntity.ok(Pedidos);
	}

	@PostMapping
	@Operation(summary = "cadastra os Pedidoss")
	public ResponseEntity<Pedidos> savePedidosControl(@RequestBody @Valid Pedidos Pedidos) {
		Pedidos savePedidos = PedidosService.savePedidos(Pedidos);
		return ResponseEntity.status(HttpStatus.CREATED).body(savePedidos);
	}

	@PutMapping("/{id}")
	@Operation(summary = "altera os Pedidoss")
	public ResponseEntity<Pedidos> alteraPedidosControl(@PathVariable Long id, @RequestBody @Valid Pedidos Pedidos) {
		Pedidos alteraPedidos = PedidosService.changePedidos(id, Pedidos);

		if (alteraPedidos != null) {
			return ResponseEntity.ok(Pedidos);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/{id}")
	@Operation(summary = "deleta os Pedidoss")
	public ResponseEntity<String> deletePedidosControl(@PathVariable Long id) {
		boolean delete = PedidosService.deletePedidos(id);
		if (delete) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} else {
			return ResponseEntity.notFound().build();
		}

	}

}
