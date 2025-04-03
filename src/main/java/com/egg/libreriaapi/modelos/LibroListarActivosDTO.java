package com.egg.libreriaapi.modelos;

import lombok.Data;

@Data
public class LibroListarActivosDTO {
    private String titulo;
    private Integer ejemplares;

    public LibroListarActivosDTO() {
    }

    LibroListarActivosDTO(String titulo, Integer ejemplares) {
        this.titulo = titulo;
        this.ejemplares = ejemplares;
    }
}
