package com.duoc.RopaStore.service;

import com.duoc.RopaStore.model.Producto;
import com.duoc.RopaStore.repository.ProductoRepository;

import java.util.Comparator;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class ProductoService {

    private final ProductoRepository productoRepository;

    public ProductoService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    public List<Producto> listarProductosOrdenados() {
        return productoRepository.findAll()
                .stream()
                .sorted(Comparator.comparing(Producto::getPrecio))
                .toList();
    }

    public List<Producto> listarProductos() {
        return productoRepository.findAll();
    }

    public Producto buscarProducto(Long id) {
        return productoRepository.findById(id).orElse(null);
    }

    public Producto guardarProducto(Producto producto) {
        return productoRepository.save(producto);
    }

    public Producto actualizarProducto(Long id, Producto producto) {
        Producto productoExistente = productoRepository.findById(id).orElse(null);

        if (productoExistente == null) {
            return null;
        }

        productoExistente.setNombre(producto.getNombre());
        productoExistente.setPrecio(producto.getPrecio());
        productoExistente.setCategoria(producto.getCategoria());
        productoExistente.setStock(producto.getStock());
        productoExistente.setSucursalId(producto.getSucursalId());

        return productoRepository.save(productoExistente);
    }

    public boolean eliminarProducto(Long id) {
        if (!productoRepository.existsById(id)) {
            return false;
        }

        productoRepository.deleteById(id);
        return true;
    }
}