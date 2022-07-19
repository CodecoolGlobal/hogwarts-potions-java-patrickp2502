package com.codecool.hogwarts_potions.dao;

import com.codecool.hogwarts_potions.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RoomRepository extends JpaRepository<Room, Long> {

    @Query(value = "select distinct r.id, r.capacity from (select * from student " +
            "where pet_type like 'CAT' or pet_type like 'OWL') as students join room_residents " +
            "on students.id = room_residents.residents_id " +
            "right outer join room r on room_residents.room_id = r.id where students.id is null;",
            nativeQuery = true)
    List<Room> findRoomForRatOwner();


}
