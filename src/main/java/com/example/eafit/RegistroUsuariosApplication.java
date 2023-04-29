package com.example.eafit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.example.eafit"})
public class RegistroUsuariosApplication {

    /**
     * Requerimientos:
     * 1. Crear y guardar usuario
     * 2. Consultar usuario por ID
     * 3. Consultar usuarios por nombre
     * 4. Consultar todos los usuarios
     */
    public static void main(String[] args) {
        SpringApplication.run(RegistroUsuariosApplication.class, args);
    }
}
