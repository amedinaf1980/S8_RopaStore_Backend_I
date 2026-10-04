package com.duoc.RopaStore.repository;

import com.duoc.RopaStore.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}