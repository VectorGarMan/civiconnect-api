package com.vectorgarman.civiconnect.controller;

// Endpoint para verificar el estado de salud de la aplicación

import com.vectorgarman.civiconnect.dto.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.HealthComponent;
import org.springframework.boot.actuate.health.HealthEndpoint;
import org.springframework.boot.actuate.health.Status;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/health")
public class HealthController {

    @Autowired
    private HealthEndpoint healthEndpoint;

    @GetMapping
    public ResponseEntity<ApiResponse<Map<String, Object>>> health() {
        HealthComponent healthComponent = healthEndpoint.health();
        Status status = healthComponent.getStatus();
        
        Map<String, Object> healthData = new HashMap<>();
        healthData.put("status", status.getCode());
        healthData.put("timestamp", LocalDateTime.now());
        healthData.put("application", "CiviConnect API");
        healthData.put("version", "0.0.1-SNAPSHOT");
        
        ApiResponse<Map<String, Object>> response = new ApiResponse<>();
        response.setStatus("OK");
        response.setMensaje("Health check realizado exitosamente");
        response.setData(healthData);
        
        return ResponseEntity.ok(response);
    }
} 
