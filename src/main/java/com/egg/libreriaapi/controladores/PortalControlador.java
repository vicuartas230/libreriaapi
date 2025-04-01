package com.egg.libreriaapi.controladores;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class PortalControlador {
    
    @GetMapping("/")
    public String inicio() {
        return "Welcome to the API!";
    }
}
