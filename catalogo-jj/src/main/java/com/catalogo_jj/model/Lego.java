package com.catalogo_jj.catalogo_jj.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "LEGO")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Lego {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String tema;
    private Integer piezas;
    private Double precio;
}
