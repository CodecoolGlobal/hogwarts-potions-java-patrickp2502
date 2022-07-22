package com.codecool.hogwarts_potions.service;

import com.codecool.hogwarts_potions.dao.IngredientRepository;
import com.codecool.hogwarts_potions.dao.PotionRepository;
import com.codecool.hogwarts_potions.dao.RecipeRepository;
import com.codecool.hogwarts_potions.dao.StudentRepository;
import com.codecool.hogwarts_potions.model.Ingredient;
import com.codecool.hogwarts_potions.model.Potion;
import com.codecool.hogwarts_potions.model.Recipe;
import com.codecool.hogwarts_potions.model.Student;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.stream.Collectors;
@ExtendWith(SpringExtension.class)
//@DataJpaTest(showSql = false)
@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class RecipeRepositoryTest {
    @Autowired
    private IngredientService ingredientService;

    @Autowired

    private IngredientRepository ingredientRepository;
    @Autowired

    private PotionRepository potionRepository;
    @Autowired

    private StudentRepository studentRepository;

    @Autowired
    private RecipeRepository recipeRepository;


    void setUp() {
        Ingredient apple = Ingredient.builder().name("apple").build();
        Ingredient banana = Ingredient.builder().name("banana").build();
        Ingredient water = Ingredient.builder().name("water").build();
        Ingredient coffee = Ingredient.builder().name("coffee").build();
        Ingredient orange = Ingredient.builder().name("orange").build();
        List<Ingredient> testIngredients = List.of(apple, banana, water, coffee, orange);
        Student testStudent = studentRepository.save(Student.builder().name("tester").build());

        testIngredients = testIngredients.stream()
                .map(ingredientRepository::save)
                .collect(Collectors.toList());

        potionRepository.save(Potion.builder()
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

        recipeRepository.save(
                Recipe.builder()
                        .name("recipe 2")
                        .ingredients(List.of(
                                testIngredients.get(0),
                                testIngredients.get(1)))
                        .build());

        Ingredient pepper = Ingredient.builder()
                .name("pepper")
                .build();
        ingredientRepository.save(pepper);
        recipeRepository.save(
                Recipe.builder()
                        .name("recipe 3")
                        .ingredients(List.of(pepper))
                        .build()
        );

    }


    @Test
    void testGetRecipeByIngredients() {
        //setUp();
        Recipe expectedRecipe = recipeRepository.findRecipeByName("testRecipe");
        System.out.println("expectedRecipe = " + expectedRecipe);
        Ingredient banana = ingredientRepository.findIngredientByName("banana").get();
        List<Long> ingredientIds = ingredientService.getIdsOfIngredients(List.of(banana));
        System.out.println("ingredientIds = " + ingredientIds);


        Recipe exampleRecipe =
                Recipe.builder().ingredients(expectedRecipe.getIngredients()).build();
        //Recipe resultRecipe = recipeRepository.findRecipeByIngredients(ingredientRepository.findAll());
        System.out.println("exampleRecipe = " + exampleRecipe);
        //List<Recipe> resultRecipe = recipeRepository.findAll(Example.of(exampleRecipe));
        //List<Recipe> resultRecipe = recipeRepository.findByIngredientsIn(exampleRecipe.getIngredients());
        List<Recipe> resultRecipe = recipeRepository.getRecipesWith(ingredientIds);
        System.out.println("resultRecipe = " + resultRecipe);


        Assertions.assertFalse(resultRecipe.isEmpty());
        Assertions.assertEquals(expectedRecipe, resultRecipe.get(0));

    }


}