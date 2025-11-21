package com.vectorgarman.civiconnect.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;

@Service
public class TokenService {

    @Value("${jwt.secret}")
    private String secretKey;

    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(secretKey.getBytes());
    }

    /**
     * Genera un token de verificación con el email del usuario
     *
     * @param email email del usuario
     * @return token JWT con caducidad de 48 horas
     */
    public String generarTokenVerificacion(String email, Long duracion) {
//        long duracion48Horas = 48 * 60 * 60 * 1000; // 48 horas en milisegundos
//        // 10 * 1000; // 10 segundos
        Date ahora = new Date();
        Date expiracion = new Date(ahora.getTime() + duracion);

        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(ahora)
                .setExpiration(expiracion)
                .claim("email", email)
                .claim("tipo", "verificacion")
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    /**
     * Valida un token y extrae el email
     *
     * @param token token a validar
     * @return email del usuario si el token es válido
     * @throws RuntimeException si el token es inválido o expirado
     */
    public String validarToken(String token) {
        try {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(getSigningKey())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

            return claims.get("email", String.class);
        } catch (Exception e) {
            System.out.println("Token inválido o expirado: " + e.getMessage());
            return "";
        }
    }

    /**
     * Verifica si un token ha expirado
     *
     * @param token token a verificar
     * @return true si el token expiró, false en caso contrario
     */
    public boolean tokenExpirado(String token) {
        try {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(getSigningKey())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

            return claims.getExpiration().before(new Date());
        } catch (Exception e) {
            return true; // Si hay error, consideramos que expiró
        }
    }

    /**
     * Obtiene la fecha de expiración del token
     *
     * @param token token a consultar
     * @return fecha de expiración
     */
    public Date obtenerExpiracion(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();

        return claims.getExpiration();
    }
}