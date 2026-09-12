package com.duoc.RopaStore.controller;

import com.duoc.RopaStore.model.Producto;
import com.duoc.RopaStore.service.ProductoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductoController {

    private final ProductoService productoService;

    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    @GetMapping("/productos")
    public List<Producto> listarProductos() {
        return productoService.listarProductosOrdenados();
    }
}