package com.catalogo_jj.catalogo_jj.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Lego")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Lego {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(unique = true, nullable = false, length = 100)
    private String nombre;

    @NotBlank
    @Column(nullable = false, length = 50)
    private String tema;

    @NotNull
    private Integer piezas;

    @NotNull
    @Column(precision = 10, scale = 2)
    private Double precio;
}
