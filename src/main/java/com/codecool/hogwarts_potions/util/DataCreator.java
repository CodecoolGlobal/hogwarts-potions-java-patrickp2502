package com.codecool.hogwarts_potions.util;

import com.codecool.hogwarts_potions.dao.IngredientRepository;
import com.codecool.hogwarts_potions.dao.PotionRepository;
import com.codecool.hogwarts_potions.dao.RecipeRepository;
import com.codecool.hogwarts_potions.dao.StudentRepository;
import com.codecool.hogwarts_potions.model.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
@Component
public class DataCreator {


    private final IngredientRepository ingredientRepository;

    private final PotionRepository potionRepository;

    private final StudentRepository studentRepository;


    private final RecipeRepository recipeRepository;


    public DataCreator(IngredientRepository ingredientRepository, PotionRepository potionRepository, StudentRepository studentRepository, RecipeRepository recipeRepository) {
        this.ingredientRepository = ingredientRepository;
        this.potionRepository = potionRepository;
        this.studentRepository = studentRepository;
        this.recipeRepository = recipeRepository;
        setUp();
    }


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


        recipeRepository.save(Recipe.builder()
                        .name("new")
                        .ingredients(List.of(water, water, water))
                .build());

    }


    public void initialize() {

        Set<Student> students = Set.of(
                Student.builder().name("Hermione Granger").petType(PetType.OWL).build(),
                Student.builder().name("Draco Malfoy").petType(PetType.CAT).build(),
                Student.builder().name("Neville").petType(PetType.NONE).build(),
                Student.builder().name("Ron").petType(PetType.RAT).build(),
                Student.builder().name("Donny").petType(PetType.RAT).build(),
                Student.builder().name("OgggOgg").petType(PetType.RAT).build(),
                Student.builder().name("Buubba").petType(PetType.RAT).build(),
                Student.builder().name("Mette").petType(PetType.RAT).build(),
                Student.builder().name("Lollo").petType(PetType.RAT).build()
        );


    }


}
