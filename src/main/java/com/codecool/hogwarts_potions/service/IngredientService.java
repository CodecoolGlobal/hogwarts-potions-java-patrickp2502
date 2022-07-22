package com.codecool.hogwarts_potions.service;

import com.codecool.hogwarts_potions.dao.IngredientRepository;
import com.codecool.hogwarts_potions.model.Ingredient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Repository
public class IngredientService {

    private final IngredientRepository ingredientRepository;


    public Ingredient getIngredientBy(String name) {
        if (name == null) {
            throw new IllegalArgumentException("name cant be null!");
        }

        Optional<Ingredient> ingredientOptional = ingredientRepository.findIngredientByName(name);
        if (ingredientOptional.isEmpty()) {
            return createIngredient(name);
        }
        return ingredientOptional.get();
    }

    public List<Long> getIdsOfIngredients(List<Ingredient> ingredients) {
        return ingredients.stream()
                .map(Ingredient::getId)
                .collect(Collectors.toList());
    }


    private Ingredient createIngredient(String name) {
        Ingredient newIngredient = Ingredient.builder().name(name).build();
        return ingredientRepository.save(newIngredient);
    }

}
