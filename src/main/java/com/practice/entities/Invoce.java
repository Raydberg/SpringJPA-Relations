package com.practice.entities;

import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "invoces")
@ToString
public class Invoce {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private Long total;
    /**
     * Muchas Facturas para un Client
     */
    @ManyToOne
    @JoinColumn(name = "id_client") //-> el nombre con el que se generara en la db
    private Client client; //-> en la db sale como client_id
}
