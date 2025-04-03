package com.egg.libreriaapi.entidades;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

@Entity
@Data
public class Libro {
    @Id
    private Long isbn;

    @Column(nullable = false)
    private String titulo;

    private Integer ejemplares = 1;

    @Temporal(TemporalType.DATE)
    private Date alta;

    private boolean activo;

    @ManyToOne
    @JoinColumn(name = "id_autor")
    private Autor autor;

    @ManyToOne
    @JoinColumn(name = "id_editorial")
    private Editorial editorial;
}
