package com.transporte.gestion.service;

import org.springframework.stereotype.Service;

import com.transporte.gestion.model.Camion;
import com.transporte.gestion.model.Conductor;
import com.transporte.gestion.repository.CamionRepository;
import com.transporte.gestion.repository.ConductorRepository;

@Service
public class AsociacionService {

    private final CamionRepository camionRepository;
    private final ConductorRepository conductorRepository;

    public AsociacionService(
            CamionRepository camionRepository,
            ConductorRepository conductorRepository) {

        this.camionRepository = camionRepository;
        this.conductorRepository = conductorRepository;
    }

    public void asociarConductor(Long camionId, Long conductorId) {

        Camion camion = camionRepository.findById(camionId)
                .orElseThrow(() -> new RuntimeException("Camión no encontrado"));

        Conductor conductor = conductorRepository.findById(conductorId)
                .orElseThrow(() -> new RuntimeException("Conductor no encontrado"));

        camion.setConductor(conductor);

        camionRepository.save(camion);
    }
}