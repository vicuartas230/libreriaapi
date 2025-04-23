package com.egg.libreriaapi.controladores;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.egg.libreriaapi.entidades.Autor;
import com.egg.libreriaapi.modelos.AutorListarDTO;
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

    @GetMapping("/listar")
    public ResponseEntity<List<Autor>> listarAutores() {
        try {
            return new ResponseEntity<>(autorServicio.listarAutores(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping("/modificar")
    public ResponseEntity<Void> modificarAutor(@RequestParam String nombre, @RequestParam String id) {
        try {
            autorServicio.modificarAutor(id, nombre, true);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/listarAutor/{id}")
    public ResponseEntity<AutorListarDTO> listarAutor(@PathVariable String id) {
        try {
            return new ResponseEntity<>(autorServicio.listarAutor(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Object> eliminarAutor(@PathVariable String id) {
        try {
            autorServicio.eliminarAutor(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
