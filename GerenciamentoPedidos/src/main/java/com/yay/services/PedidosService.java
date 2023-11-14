package com.yay.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yay.entities.Pedidos;
import com.yay.repository.PedidosRepository;

@Service
public class PedidosService {
	private final PedidosRepository PedidosRepository;
	

	@Autowired
	public PedidosService(PedidosRepository PedidosRepository) {
		this.PedidosRepository = PedidosRepository;
	}

	public List<Pedidos> getAllPedidoss() {
		return PedidosRepository.findAll();
	}

	public Pedidos getPedidosById(Long id) {
		Optional<Pedidos> Pedidos = PedidosRepository.findById(id);
		return Pedidos.orElse(null);
	}

	public Pedidos savePedidos(Pedidos Pedidos) {
		return PedidosRepository.save(Pedidos);
	}

	public Pedidos changePedidos(Long id, Pedidos changeU) {
		Optional<Pedidos> existePedidos = PedidosRepository.findById(id);
		if (existePedidos.isPresent()) {
			changeU.setId(id);
			return PedidosRepository.save(changeU);
		}
		return null;
	}

	public boolean deletePedidos(Long id) {
		Optional<Pedidos> existePedidos= PedidosRepository.findById(id);
		if (existePedidos.isPresent()) {
			PedidosRepository.deleteById(id);
			return true;
		}
		return false;
	}

}