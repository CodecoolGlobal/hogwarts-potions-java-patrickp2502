package com.codecool.hogwarts_potions.model;


import lombok.*;
import org.hibernate.annotations.ManyToAny;

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
public class Potion {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    @ManyToOne
    private Student student;
    @Enumerated(value = EnumType.STRING)
    private BrewingStatus brewingStatus;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Ingredient> ingredients;


}
