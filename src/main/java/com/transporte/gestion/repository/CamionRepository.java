package com.transporte.gestion.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.transporte.gestion.model.Camion;

public interface CamionRepository extends JpaRepository<Camion, Long> {
}