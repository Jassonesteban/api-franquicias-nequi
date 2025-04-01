package com.apifranquicias.franquicias.domain.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table("sucursal")
public class Sucursal {

    @Id
    private Long id;
    private String nombre;
    private Integer franquiciaId;
}
