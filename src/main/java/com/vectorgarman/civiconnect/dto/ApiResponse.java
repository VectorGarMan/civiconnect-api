package com.vectorgarman.civiconnect.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse<T> {
    private String status;   // "OK", "ERROR"
    private String mensaje;  // mensaje para el usuario
    private String error;    // error técnico (opcional)
    private T data;          // cuerpo de la respuesta (DTOs, entidades, listas, etc.)
}
