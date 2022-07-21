package com.codecool.hogwarts_potions.service;

import com.codecool.hogwarts_potions.dao.IngredientRepository;
import com.codecool.hogwarts_potions.dao.PotionRepository;
import com.codecool.hogwarts_potions.dao.RecipeRepository;
import com.codecool.hogwarts_potions.dao.StudentRepository;
import com.codecool.hogwarts_potions.model.Ingredient;
import com.codecool.hogwarts_potions.model.Potion;
import com.codecool.hogwarts_potions.model.Recipe;
import com.codecool.hogwarts_potions.model.Student;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.stream.Collectors;
@ExtendWith(SpringExtension.class)
@DataJpaTest(showSql = true)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class RecipeServiceTest {

    @Autowired

    private IngredientRepository ingredientRepository;
    @Autowired

    private  PotionRepository potionRepository;
    @Autowired

    private  StudentRepository studentRepository;

    @Autowired
    private RecipeRepository recipeRepository;

   void setUp() {
        Ingredient apple = Ingredient.builder().name("apple").build();
        Ingredient banana = Ingredient.builder().name("banana").build();
        Ingredient water = Ingredient.builder().name("water").build();
        Ingredient coffee = Ingredient.builder().name("coffee").build();
        Ingredient orange = Ingredient.builder().name("orange").build();
        List<Ingredient> testIngredients = List.of(apple, banana, water, coffee, orange);
        Student testStudent =  studentRepository.save(Student.builder().name("tester").build());

        testIngredients = testIngredients.stream()
                .map(ingredientRepository::save)
                .collect(Collectors.toList());

        Potion testPotion = potionRepository.save(Potion.builder()
                .name("testPotion1")
                .student(testStudent)
                .ingredients(testIngredients)
                .build());

        recipeRepository.save(
                Recipe.builder()
                .name("testRecipe")
                .ingredients(testIngredients)
                .student(testStudent)
                .build());

        recipeRepository.save(Recipe.builder()
                .ingredients(List.of(testIngredients.get(0), testIngredients.get(1))).build());

    }



    @Test
    void testGetRecipeByIngredients() {
        setUp();

        Recipe expectedRecipe = recipeRepository.findRecipeByName("testRecipe");
        Recipe resultRecipe = recipeRepository.findRecipeByIngredients(ingredientRepository.findAll());
        System.out.println("resultRecipe = " + resultRecipe);
        Assertions.assertEquals(expectedRecipe, resultRecipe);

    }


}