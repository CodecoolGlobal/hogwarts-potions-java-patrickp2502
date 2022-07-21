package com.codecool.hogwarts_potions.dao;

import com.codecool.hogwarts_potions.model.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IngredientRepository extends JpaRepository<Ingredient, Long> {

    public Optional<Ingredient> findIngredientByName(String name);
}
