package com.apifranquicias.franquicias.domain.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table("producto")
public class Producto {

    @Id
    private Long id;
    private String nombre;
    private Integer stock;
    private Long sucursalId;
}
