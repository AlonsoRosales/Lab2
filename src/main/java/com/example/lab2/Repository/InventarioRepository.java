package com.example.lab2.Repository;

import com.example.lab2.Entity.Inventario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventarioRepository extends JpaRepository<Inventario,Integer> {
}
