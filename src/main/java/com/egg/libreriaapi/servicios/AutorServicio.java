package com.egg.libreriaapi.servicios;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.egg.libreriaapi.entidades.Autor;
import com.egg.libreriaapi.excepciones.MiExcepcion;
import com.egg.libreriaapi.repositorios.AutorRepositorio;


@Service
public class AutorServicio {

    private AutorRepositorio autorRepositorio;

    public AutorServicio(AutorRepositorio autorRepositorio) {
        this.autorRepositorio = autorRepositorio;
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

    private void validar(String nombre) throws MiExcepcion {
        if (nombre == null || nombre.isEmpty()) {
            throw new MiExcepcion("El nombre del autor no puede ser nulo.");
        }
    }
}