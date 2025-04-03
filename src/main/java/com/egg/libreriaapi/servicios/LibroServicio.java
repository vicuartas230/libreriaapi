package com.egg.libreriaapi.servicios;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.egg.libreriaapi.entidades.Autor;
import com.egg.libreriaapi.entidades.Editorial;
import com.egg.libreriaapi.entidades.Libro;
import com.egg.libreriaapi.modelos.LibroCreateDTO;
import com.egg.libreriaapi.modelos.LibroListarActivosDTO;
import com.egg.libreriaapi.repositorios.LibroRepositorio;


@Service
public class LibroServicio {
    @Autowired
    private LibroRepositorio libroRepositorio;


    @Autowired
    private AutorServicio autorServicio;


    @Autowired
    private EditorialServicio editorialServicio;


    @Transactional
    public void crearLibro(LibroCreateDTO libroCreateDTO) {
        Libro libroNvo = new Libro();
        libroNvo.setIsbn(libroCreateDTO.getIsbn());
        libroNvo.setTitulo(libroCreateDTO.getTitulo());
        libroNvo.setEjemplares(libroCreateDTO.getEjemplares());
        libroNvo.setActivo(libroCreateDTO.isLibroActivo());
        Optional<Autor> respuesta = autorServicio.getOne(libroCreateDTO.getIdAutor());
        if (respuesta.isPresent()) {
            libroNvo.setAutor(respuesta.get());
        }
        Optional<Editorial> res = editorialServicio.getOne(libroCreateDTO.getIdEditorial());
        if (res.isPresent()) {
            libroNvo.setEditorial(res.get());
        }
        libroRepositorio.save(libroNvo);
    }

    @Transactional
    public List<LibroListarActivosDTO> listarLibrosActivos() {
        return libroRepositorio.encontrarActivos();
    }
}
