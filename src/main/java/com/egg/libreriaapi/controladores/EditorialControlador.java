package com.egg.libreriaapi.controladores;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.egg.libreriaapi.entidades.Editorial;
import com.egg.libreriaapi.servicios.EditorialServicio;

@RestController
@RequestMapping("/editorial")
public class EditorialControlador {

    private EditorialServicio editorialServicio;
    
    public EditorialControlador(EditorialServicio editorialServicio) {
        this.editorialServicio = editorialServicio;
    }

    @PostMapping("/crear")
    public ResponseEntity<Editorial> crearEditorial(String nombre) {
        try {
            editorialServicio.crearEditorial(nombre);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
