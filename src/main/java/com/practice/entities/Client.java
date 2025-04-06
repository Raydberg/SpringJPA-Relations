package com.practice.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "clients")
@ToString
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String lastName;
    /**
     * si ponemos CascadeType.ALL que cuando se elimine o se cree o se actualice
     * va afectar tambien a la tabla Adreess
     * MERGE -> Actualizar
     * PERSIST -> Crear
     * DELETE ->  Eliminar
     */
    @OneToMany(cascade = CascadeType.ALL,orphanRemoval = true)
    // Crea tabla intermedia
    private List<Address> addresses = new ArrayList<>();
}
