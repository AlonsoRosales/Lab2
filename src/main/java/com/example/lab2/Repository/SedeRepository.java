package com.example.lab2.Repository;

import com.example.lab2.Entity.Sede;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.criteria.CriteriaBuilder;

public interface SedeRepository extends JpaRepository<Sede, Integer> {
}
