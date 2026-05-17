package com.piezas_jj.piezas_jj.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "piezas")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Pieza {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String categoria;
    private String color;
    private Integer stock;
    private Double precio;
}
