package com.egg.libreriaapi.controladores;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.egg.libreriaapi.entidades.Libro;
import com.egg.libreriaapi.modelos.LibroCreateDTO;
import com.egg.libreriaapi.modelos.LibroListarActivosDTO;
import com.egg.libreriaapi.servicios.LibroServicio;


@RestController
@RequestMapping("/libro")
public class LibroControlador {
    @Autowired
    private LibroServicio libroServicio;


    @PostMapping("/crear")
    public ResponseEntity<Object> crearLibro(@RequestBody LibroCreateDTO libroDTO) {
        try {
            libroServicio.crearLibro(libroDTO);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("{\"Algun dato no es correcto o es nulo, revisar.\"}");
        }
    }

    @GetMapping("/listar")
    public ResponseEntity<Object> listarLibrosActivos() {
        try {
            List<LibroListarActivosDTO> libros = libroServicio.listarLibrosActivos();
            return new ResponseEntity<>(libros, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
