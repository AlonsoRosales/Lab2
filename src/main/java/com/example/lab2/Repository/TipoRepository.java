package com.example.lab2.Repository;
import com.example.lab2.Entity.Tipo;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.criteria.CriteriaBuilder;


public interface TipoRepository extends JpaRepository<Tipo, Integer> {
}
