package com.yay.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yay.entities.Pedidos;

public interface PedidosRepository extends JpaRepository<Pedidos,Long> {

}