package com.codecool.hogwarts_potions.service;

import com.codecool.hogwarts_potions.dao.PotionRepository;
import com.codecool.hogwarts_potions.dao.StudentRepository;
import com.codecool.hogwarts_potions.model.Ingredient;
import com.codecool.hogwarts_potions.model.Potion;
import com.codecool.hogwarts_potions.model.Student;
import com.codecool.hogwarts_potions.service.constants.BrewingServiceConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class BrewingService {

    private final PotionRepository potionRepository;
    private final StudentRepository studentRepository;

    private final IngredientService ingredientService;
    private final RecipeService recipeService;

    public List<Potion> getAllPotions() {
        return potionRepository.findAll();

    }


    public Optional<Potion> createPotion(long studentId) {
        Optional<Student> student = studentRepository.findById(studentId);
        if (student.isEmpty()) {
            return Optional.empty();
        }
        Potion newPotion = Potion.builder().student(student.get()).build();
        Potion savedPotion = potionRepository.save(newPotion);
        return Optional.of(savedPotion);
    }

    /**
     * searches a potion by id and adds the ingredient to the potion.
     * @param potionId
     * @param ingredientName
     * @return empty if no potion is found
     */
    public Optional<Potion> brewPotion(Long potionId, String ingredientName) {
        Optional<Potion> potionOptional = potionRepository.findById(potionId);
        if (potionOptional.isEmpty()) {
            return Optional.empty();
        }

        Ingredient ingredient = ingredientService.getIngredientBy(ingredientName);
        List<Ingredient> potionsIngredientsList = potionOptional.get().getIngredients();
        potionsIngredientsList.add(ingredient);
        processBrewingStatus(potionOptional.get());
        return Optional.of(potionRepository.save(potionOptional.get()));
    }

    public void processBrewingStatus(Potion potion) {
        List<Ingredient> ingredients = potion.getIngredients();
        if (ingredients.size() < BrewingServiceConstants.MAX_INGREDIENTS_FOR_POTIONS) {
            return;
        }
        




    }


}
