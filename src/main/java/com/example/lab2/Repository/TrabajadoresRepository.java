package com.example.lab2.Repository;

import com.example.lab2.Entity.Trabajadores;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TrabajadoresRepository extends JpaRepository<Trabajadores,String> {

}
