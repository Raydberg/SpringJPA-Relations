package com.practice.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(name = "last_name")
    private String lastName;
    //Solo se puede usar el PERSIST Y EL MERGE
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "tbl_alumnos_cursos",
            joinColumns = @JoinColumn(name = "id_student"),
            inverseJoinColumns = @JoinColumn(name = "id_curso"),
            uniqueConstraints = @UniqueConstraint(columnNames = {"id_student", "id_curso"})
    )
    private Set<Course> cursos = new HashSet<>();

}
