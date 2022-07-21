package com.codecool.hogwarts_potions.dao;

import com.codecool.hogwarts_potions.model.Ingredient;
import com.codecool.hogwarts_potions.model.Recipe;
import com.codecool.hogwarts_potions.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {


    List<Recipe> getRecipesByStudent(Student student);


    
    @Query(value = "SELECT DISTINCT FROM recipe " +
            "JOIN recipe_ingredients on recipe.id = recipe_ingredients.recipe_id" +
            "", nativeQuery = true)
    Recipe findRecipeByIngredients(List<Ingredient> ingredients);

    Recipe findRecipeByName(String name);
}
