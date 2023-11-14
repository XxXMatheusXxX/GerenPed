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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yay.entities.Pedidos;
import com.yay.services.PedidosService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/Pedidos")
public class PedidosController {

	private final PedidosService PedidosService;

	@Autowired
	public PedidosController(PedidosService PedidosService) {
		this.PedidosService = PedidosService;
	}

	@GetMapping("/{id}")
	public ResponseEntity<Pedidos> buscaPedidosControlId(@PathVariable Long id) {
		Pedidos Pedidos = PedidosService.getPedidosById(id);
		if (Pedidos != null) {
			return ResponseEntity.ok(Pedidos);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping("/")
	public ResponseEntity<List<Pedidos>> buscaTodasLigacoesControl() {
		List<Pedidos> Pedidos = PedidosService.getAllPedidoss();
		return ResponseEntity.ok(Pedidos);
	}

	@PostMapping("/")
	public ResponseEntity<Pedidos> savePedidosControl(@RequestBody @Valid Pedidos Pedidos) {
		Pedidos savePedidos = PedidosService.savePedidos(Pedidos);
		return ResponseEntity.status(HttpStatus.CREATED).body(savePedidos);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Pedidos> alteraPedidosControl(@PathVariable Long id, @RequestBody @Valid Pedidos Pedidos) {
		Pedidos alteraPedidos = PedidosService.changePedidos(id, Pedidos);

		if (alteraPedidos != null) {
			return ResponseEntity.ok(Pedidos);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deletePedidosControl(@PathVariable Long id) {
		boolean delete = PedidosService.deletePedidos(id);
		if (delete) {
			return ResponseEntity.ok().body("O Pedidos foi excluido com o sucesso");
		} else {
			return ResponseEntity.notFound().build();
		}

	}

}