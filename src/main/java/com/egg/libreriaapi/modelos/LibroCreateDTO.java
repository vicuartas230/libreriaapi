package com.egg.libreriaapi.modelos;


import lombok.Data;


@Data
public class LibroCreateDTO {
    private Long isbn;//Este dato sera utilizado como idLibro
    private String titulo;
    private Integer ejemplares;
    private String idAutor;
    private String idEditorial;
    private boolean libroActivo;
}
