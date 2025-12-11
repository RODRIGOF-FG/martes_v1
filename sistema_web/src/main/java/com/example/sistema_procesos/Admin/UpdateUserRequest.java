package com.example.sistema_procesos.Admin;

import lombok.Data;

@Data
public class UpdateUserRequest {
    private String email;
    private String password;
    private String firstname;
    private String lastname;
    private String country;
    private String role;
}

