package com.codecool.hogwarts_potions.service;

import com.codecool.hogwarts_potions.dao.RecipeRepository;
import com.codecool.hogwarts_potions.model.Ingredient;
import com.codecool.hogwarts_potions.model.Recipe;
import com.codecool.hogwarts_potions.model.Student;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RecipeService {

    private final RecipeRepository recipeRepository;

    public Recipe createRecipe(Student student, List<Ingredient> ingredients) {
        int newStudentRecipeCount = recipeRepository.getRecipesByStudent(student).size() + 1;
        String recipeName = String.format("%s %s #%d",
                student.getName(),
                "discovery",
                newStudentRecipeCount);
        Recipe recipe = Recipe.builder()
                .student(student)
                .ingredients(ingredients)
                .name(recipeName).build();

        return recipeRepository.save(recipe);
    }

    public List<Recipe> getAllRecipes() {
        return recipeRepository.findAll();
    }

    public List<Recipe> getRecipesBy(Student student) {
        return recipeRepository.getRecipesByStudent(student);

    }


    public boolean isDiscovery(List<Ingredient> ingredients) {
        //return recipeRepository.findRecipeByIngredientsIn(ingredients).isEmpty();
        return false;
    }


}
