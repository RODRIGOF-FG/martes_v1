package com.example.sistema_procesos.Admin;

import lombok.Data;

@Data
public class CreateUserRequest {
    private String email;
    private String password;
    private String firstname;
    private String lastname;
    private String country;
    private String role; // "ADMIN" or "ROLE_ADMIN" or "OPERADOR" or "ROLE_OPERADOR"

    public String normalizedRole() {
        if (role == null) return null;
        String r = role.trim().toUpperCase();
        if (r.equals("ADMIN") || r.equals("ROLE_ADMIN")) return "ROLE_ADMIN";
        if (r.equals("OPERADOR") || r.equals("ROLE_OPERADOR")) return "ROLE_OPERADOR";
        throw new IllegalArgumentException("Rol inválido. Use ADMIN u OPERADOR");
    }
}
