package com.auth_jj.auth_jj.config;

import com.auth_jj.auth_jj.dto.UsuarioResponseDTO;
import com.auth_jj.auth_jj.model.Autenticacion;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtService {

    // Llave secreta para firmar el token (En producción esto va en application.properties)
    private static final Key SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    // Tiempo de vida del token (1 día en milisegundos)
    private static final long EXPIRATION_TIME = 86400000;

    public String generarToken(Autenticacion user) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("rol", user.getRol());
        claims.put("idUsuario", user.getIdUsuarioRef());

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(user.getUsername()) // Usamos username como subject
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 86400000))
                .signWith(SECRET_KEY)
                .compact();
    }

    public boolean validarToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(SECRET_KEY).build().parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public String extraerUsername(String token) {
        return extraerClaims(token).getSubject();
    }

    public String extraerRol(String token) {
        return (String) extraerClaims(token).get("rol");
    }

    private Claims extraerClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
