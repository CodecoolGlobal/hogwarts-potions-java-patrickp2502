package com.codecool.hogwarts_potions.model;

import lombok.*;
import org.hibernate.annotations.CollectionType;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
@ToString
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    String name;
    @ManyToOne
    Student student;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Ingredient> ingredients;
}
