package com.egg.libreriaapi.servicios;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.egg.libreriaapi.entidades.Editorial;
import com.egg.libreriaapi.excepciones.MiExcepcion;
import com.egg.libreriaapi.repositorios.EditorialRepositorio;

@Service
public class EditorialServicio {

    private EditorialRepositorio editorialRepositorio;

    public EditorialServicio(EditorialRepositorio editorialRepositorio) {
        this.editorialRepositorio = editorialRepositorio;
    }

    @Transactional
    public void crearEditorial(String nombre) throws MiExcepcion {
        validar(nombre);
        Editorial editorial = new Editorial();
        editorial.setNombre(nombre);
        editorial.setActivo(true);
        editorialRepositorio.save(editorial);
    }

    @Transactional(readOnly = true)
    public List<Editorial> listarEditoriales() {
        return editorialRepositorio.findAll();
    }

    @Transactional
    public Editorial modificarEditorial(String id, String nombre, boolean activo) throws MiExcepcion {
        validar(nombre);
        Optional<Editorial> respuesta = getOne(id);
        if (respuesta.isPresent()) {
            respuesta.get().setNombre(nombre);
            respuesta.get().setActivo(activo);
            editorialRepositorio.save(respuesta.get());
            return respuesta.get();
        }
        return null;
    }

    @Transactional(readOnly = true)
    public Optional<Editorial> getOne(String id) {
        return editorialRepositorio.findById(id);
    }

    @Transactional
    public void eliminar(String id) {
        Optional<Editorial> respuesta = getOne(id);
        if (respuesta.isPresent()) {
            respuesta.get().setActivo(false);
        }
    }

    private void validar(String nombre) throws MiExcepcion {
        if (nombre == null || nombre.isEmpty()) {
            throw new MiExcepcion("El nombre de la editorial no puede ser nulo.");
        }
    }
}
