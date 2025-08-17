package com.practice.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "clients")
@ToString(exclude = {"addresses", "facturas"})
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
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinTable(name = "tbl_clientes_to_direcciones",
            joinColumns = @JoinColumn(name = "id_cliente"),
            inverseJoinColumns = @JoinColumn(name = "id_direccion"),
            // Proteccion de direcciones repetidas a nivel DB
            uniqueConstraints = @UniqueConstraint(columnNames = {"id_direccion"})
    )
    //Proteccion de direcciones repetirdad a nivel Java
    private Set<Address> addresses = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "client")
    public Set<Invoice> facturas = new HashSet<>();

    @OneToOne(fetch = FetchType.LAZY)
    private ClientDetails clientDetails;
}
