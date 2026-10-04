package com.duoc.RopaStore.controller;

import com.duoc.RopaStore.model.Producto;
import com.duoc.RopaStore.model.Sucursal;
import com.duoc.RopaStore.service.SucursalService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sucursales")
public class SucursalController {

    private final SucursalService sucursalService;

    public SucursalController(SucursalService sucursalService) {
        this.sucursalService = sucursalService;
    }

    @PostMapping
    public ResponseEntity<Sucursal> crearSucursal(@RequestBody Sucursal sucursal) {
        return ResponseEntity.ok(sucursalService.guardarSucursal(sucursal));
    }

    @GetMapping("/{sucursalId}/inventario")
    public List<Producto> listarInventario(@PathVariable Long sucursalId) {
        return sucursalService.listarInventario(sucursalId);
    }
}