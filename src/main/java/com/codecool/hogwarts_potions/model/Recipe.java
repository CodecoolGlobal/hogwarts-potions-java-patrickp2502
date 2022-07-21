package com.codecool.hogwarts_potions.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
    String name;
    @ManyToOne
    Student student;
    @ManyToMany
    List<Ingredient> ingredients;
}
