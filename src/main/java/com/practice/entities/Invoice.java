package com.practice.entities;

import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "invoces")
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private Long total;
    /**
     * Muchas Facturas para un Client
     */
    @ManyToOne(fetch = FetchType.LAZY)
    // El dueño de la relacion
    @JoinColumn(name = "id_client")
    //-> el nombre con el que se generara en la db
    private Client client; //-> en la db sale como client_id


}
