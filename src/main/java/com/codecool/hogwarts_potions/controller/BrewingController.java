package com.codecool.hogwarts_potions.controller;

import com.codecool.hogwarts_potions.model.Potion;
import com.codecool.hogwarts_potions.service.BrewingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
public class BrewingController {

    private final BrewingService brewingService;
    private final PotionService potionService;

    @GetMapping("/potions")
    public List<Potion> getAllPotions() {

        return brewingService.getAllPotions();
    }


    @PostMapping("/potions/brew")
    public Potion getNewPotion(@RequestParam(name = "student-id", required = true) long studentId) {
        Optional<Potion> newPotion = brewingService.createPotion(studentId);
        if (newPotion.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Student not found by id = " + studentId);
        }
        return newPotion.get();
    }

    @PostMapping("/potions/{potion-id}/add")
    public Potion brewPotion(@PathVariable("potion-id") Long potionId,
                             @RequestParam("ingredient-name") String ingredientName) {
        Optional<Potion> potion = brewingService.brewPotion(potionId, ingredientName);
        if (potion.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Potion not found by id = " + potionId);
        }

        return potion.get();
    }


    @GetMapping("/potions/{student-id}")
    public List<Potion> getStudentExploredPotions(@PathVariable("student-id") Long studentId) {

        return potionService.getPotionsBy(studentId);
    }


}
