package com.egg.libreriaapi.controladores;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.egg.libreriaapi.entidades.Autor;
import com.egg.libreriaapi.servicios.AutorServicio;

@RestController
@RequestMapping("/autor")
public class AutorControlador {
    
    private AutorServicio autorServicio;

    public AutorControlador(AutorServicio autorServicio) {
        this.autorServicio = autorServicio;
    }

    @PostMapping("/crear")
    public ResponseEntity<Autor> crearAutor(@RequestParam String nombre) {
        try {
            autorServicio.crearAutor(nombre);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
