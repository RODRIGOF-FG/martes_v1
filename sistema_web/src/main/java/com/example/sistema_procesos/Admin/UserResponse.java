package com.example.sistema_procesos.Admin;

import com.example.sistema_procesos.User.Role;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserResponse {
    private Long id;
    private String firstname;
    private String lastname;
    private String email;
    private String country;
    private Role role;
    private boolean passwordSet;     // true si tiene contraseña
    private String maskedPassword;   // ej. "******" (NO el hash ni el texto plano)
}
