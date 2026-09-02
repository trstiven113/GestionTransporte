package com.transporte.gestion.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.transporte.gestion.model.Conductor;

public interface ConductorRepository extends JpaRepository<Conductor, Long> {
}