package com.example.sistema_procesos.admin_salida;

import com.example.sistema_procesos.User.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminSalidaService {

    private final AdminSalidaRepository repository;
    private final UserRepository userRepository;

    // ✅ SOLO ADMIN LLAMA ESTE MÉTODO
    public List<AdminSalida> listarTodas() {
        return repository.findAll();
    }
}
