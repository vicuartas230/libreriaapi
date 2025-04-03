package com.egg.libreriaapi.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.egg.libreriaapi.entidades.Libro;
import com.egg.libreriaapi.modelos.LibroListarActivosDTO;

@Repository
public interface LibroRepositorio extends JpaRepository<Libro, Long> {

    @Query("SELECT l FROM Libro l WHERE l.titulo = :titulo")
    public Libro buscarLibroPorTitulo(@Param("titulo") String titulo);

    @Query("SELECT new com.egg.libreriaapi.modelos.LibroListarActivosDTO(l.titulo, l.ejemplares) " +
            "FROM Libro l WHERE l.activo = true")
    List<LibroListarActivosDTO> encontrarActivos();
}
