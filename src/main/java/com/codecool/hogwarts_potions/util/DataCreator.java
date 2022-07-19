package com.codecool.hogwarts_potions.util;

import com.codecool.hogwarts_potions.dao.RoomRepository;
import com.codecool.hogwarts_potions.dao.StudentRepository;
import com.codecool.hogwarts_potions.model.PetType;
import com.codecool.hogwarts_potions.model.Student;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class DataCreator {

    private final RoomRepository roomRepository;
    private final StudentRepository studentRepository;


    public DataCreator(RoomRepository roomRepository, StudentRepository studentRepository) {
        this.roomRepository = roomRepository;
        this.studentRepository = studentRepository;
        initialize();
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
