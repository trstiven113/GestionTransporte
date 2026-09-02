package com.transporte.gestion.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.transporte.gestion.service.AsociacionService;

@RestController
@RequestMapping("/api/asociaciones")
public class AsociacionController {

    private final AsociacionService asociacionService;

    public AsociacionController(AsociacionService asociacionService) {
        this.asociacionService = asociacionService;
    }

    @PutMapping("/camion/{camionId}/conductor/{conductorId}")
    public ResponseEntity<String> asociarConductor(
            @PathVariable Long camionId,
            @PathVariable Long conductorId) {

        asociacionService.asociarConductor(camionId, conductorId);

        return ResponseEntity.ok(
                "Conductor asociado correctamente al camión"
        );
    }
}