package com.codecool.hogwarts_potions.controller;

import com.codecool.hogwarts_potions.dao.PotionRepository;
import com.codecool.hogwarts_potions.model.Potion;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class PotionService {


    private final PotionRepository potionRepository;


    public List<Potion> getPotionsBy(long studentId) {
        return potionRepository.findAllByStudentId(studentId);
    }

}
