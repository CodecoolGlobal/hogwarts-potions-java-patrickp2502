package com.codecool.hogwarts_potions.dao;

import com.codecool.hogwarts_potions.model.Ingredient;
import com.codecool.hogwarts_potions.model.Recipe;
import com.codecool.hogwarts_potions.model.Student;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {


    List<Recipe> getRecipesByStudent(Student student);

    /*
        @Query("select Recipe from Recipe where ingredients in :ingredients")
        List<Recipe> findRecipesByIngredients(@Param("ingredients") List<Ingredient> ingredients);
    */
    @Query(value = "select recipe.id, recipe.name from recipe " +
            "join recipe_ingredients ri on recipe.id = ri.recipe_id", nativeQuery = true)
    List<Recipe> getRecipesWith(List<Long> ingredientIds);



    Recipe findRecipeByName(String name);
}
