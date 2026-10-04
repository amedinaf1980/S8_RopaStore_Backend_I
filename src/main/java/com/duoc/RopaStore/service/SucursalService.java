package com.duoc.RopaStore.service;

import com.duoc.RopaStore.model.Producto;
import com.duoc.RopaStore.model.Sucursal;
import com.duoc.RopaStore.repository.ProductoRepository;
import com.duoc.RopaStore.repository.SucursalRepository;

import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class SucursalService {

    private final SucursalRepository sucursalRepository;
    private final ProductoRepository productoRepository;

    public SucursalService(
            SucursalRepository sucursalRepository,
            ProductoRepository productoRepository) {
        this.sucursalRepository = sucursalRepository;
        this.productoRepository = productoRepository;
    }

    public Sucursal guardarSucursal(Sucursal sucursal) {
        return sucursalRepository.save(sucursal);
    }

    public List<Producto> listarInventario(Long sucursalId) {
        return productoRepository.findAll()
                .stream()
                .filter(producto -> sucursalId.equals(producto.getSucursalId()))
                .toList();
    }
}