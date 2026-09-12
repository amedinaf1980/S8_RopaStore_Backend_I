package com.duoc.RopaStore.service;

import com.duoc.RopaStore.model.Producto;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ProductoService {

    private List<Producto> productos = new ArrayList<>();

    public ProductoService() {
        productos.add(new Producto(1, "Polera", "Ropa", 12990));
        productos.add(new Producto(2, "Pantalón", "Ropa", 24990));
        productos.add(new Producto(3, "Chaqueta", "Abrigos", 39990));
        productos.add(new Producto(4, "Vestido", "Ropa", 29990));
        productos.add(new Producto(5, "Zapatillas", "Calzado", 49990));
    }

    public List<Producto> listarProductosOrdenados() {
        productos.sort(Comparator.comparing(Producto::getPrecio));
        return productos;
    }
}