package com.vectorgarman.civiconnect.dto;

public class ApiResponse<T> {
    private String status;   // "OK", "ERROR"
    private String mensaje;  // mensaje para el usuario
    private String error;    // error técnico (opcional)
    private T data;          // cuerpo de la respuesta (DTOs, entidades, listas, etc.)

    public ApiResponse() {
    }

    public ApiResponse(String status, String mensaje, String error, T data) {
        this.status = status;
        this.mensaje = mensaje;
        this.error = error;
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
