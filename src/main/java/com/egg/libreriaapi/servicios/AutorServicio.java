package com.egg.libreriaapi.servicios;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.egg.libreriaapi.entidades.Autor;
import com.egg.libreriaapi.entidades.Libro;
import com.egg.libreriaapi.excepciones.MiExcepcion;
import com.egg.libreriaapi.modelos.AutorListarDTO;
import com.egg.libreriaapi.repositorios.AutorRepositorio;
import com.egg.libreriaapi.repositorios.LibroRepositorio;


@Service
public class AutorServicio {

    private AutorRepositorio autorRepositorio;

    private LibroRepositorio libroRepositorio;

    public AutorServicio(AutorRepositorio autorRepositorio, LibroRepositorio libroRepositorio) {
        this.autorRepositorio = autorRepositorio;
        this.libroRepositorio = libroRepositorio;
    }

    @Transactional
    public Autor crearAutor(String nombre) throws MiExcepcion {
        validar(nombre);
        Autor autor = new Autor();
        autor.setNombre(nombre);
        autor.setActivo(true);
        return autorRepositorio.save(autor);
    }

    @Transactional(readOnly = true)
    public List<Autor> listarAutores() {
        return autorRepositorio.findAll();
    }

    @Transactional
    public Autor modificarAutor(String id, String nombre, boolean activo) throws MiExcepcion {
        validar(nombre);     
        Optional<Autor> respuesta = getOne(id);
        if (respuesta.isPresent()) {
            respuesta.get().setNombre(nombre);
            respuesta.get().setActivo(activo);
            autorRepositorio.save(respuesta.get());
            return respuesta.get();
        }
        return null;
    }

    @Transactional(readOnly = true)
    public Optional<Autor> getOne(String id) {
        return autorRepositorio.findById(id);
    }

    @Transactional
    public void eliminar(String id) {
        Optional<Autor> respuesta = getOne(id);
        if (respuesta.isPresent()) {
            respuesta.get().setActivo(false);
        }
    }

    @Transactional
    public AutorListarDTO listarAutor(String id) throws Exception {
        Optional<Autor> res = getOne(id);
        if (res.isPresent()) {
            AutorListarDTO autorListar = new AutorListarDTO();
            autorListar.setNombre(res.get().getNombre());
            return autorListar;
        } else {
            throw new Exception("No se encontró el autor.");
        }
    }

    @Transactional
    public void eliminarAutor(String id) throws MiExcepcion {
        Optional<Autor> res = getOne(id);
        if (res.isPresent()) {
            List<Libro> libros = libroRepositorio.listarLibrosPorIdAutor(id);
            for (Libro libro : libros) {
                libro.setActivo(false);
            }
            res.get().setActivo(false);
        } else throw new MiExcepcion("No se encontró el autor.");
    }

    private void validar(String nombre) throws MiExcepcion {
        if (nombre == null || nombre.isEmpty()) {
            throw new MiExcepcion("El nombre del autor no puede ser nulo.");
        }
    }
}