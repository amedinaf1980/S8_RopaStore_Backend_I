package com.duoc.RopaStore.service;

import com.duoc.RopaStore.model.Pedido;
import com.duoc.RopaStore.repository.PedidoRepository;

import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class PedidoService {

    private final PedidoRepository pedidoRepository;

    public PedidoService(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    public List<Pedido> listarPedidos() {
        return pedidoRepository.findAll();
    }

    public List<Pedido> buscarPorCliente(Long clienteId) {
        return pedidoRepository.findByClienteId(clienteId);
    }

    public Pedido buscarPedido(Long id) {
        return pedidoRepository.findById(id).orElse(null);
    }

    public Pedido guardarPedido(Pedido pedido) {
        return pedidoRepository.save(pedido);
    }

    public Pedido actualizarPedido(Long id, Pedido pedido) {
        Pedido pedidoExistente = pedidoRepository.findById(id).orElse(null);

        if (pedidoExistente == null) {
            return null;
        }

        pedidoExistente.setClienteId(pedido.getClienteId());
        pedidoExistente.setFechaPedido(pedido.getFechaPedido());
        pedidoExistente.setTotal(pedido.getTotal());
        pedidoExistente.setEstado(pedido.getEstado());

        return pedidoRepository.save(pedidoExistente);
    }

    public boolean eliminarPedido(Long id) {
        if (!pedidoRepository.existsById(id)) {
            return false;
        }

        pedidoRepository.deleteById(id);
        return true;
    }
}