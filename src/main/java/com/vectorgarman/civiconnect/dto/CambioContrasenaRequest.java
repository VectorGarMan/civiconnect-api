package com.vectorgarman.civiconnect.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CambioContrasenaRequest {
    String token;
    String nuevaContrasena;
    String email;
}
