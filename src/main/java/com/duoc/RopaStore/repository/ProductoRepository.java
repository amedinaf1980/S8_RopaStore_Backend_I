package com.duoc.RopaStore.repository;

import com.duoc.RopaStore.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
}