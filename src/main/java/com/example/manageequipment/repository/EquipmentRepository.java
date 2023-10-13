package com.example.manageequipment.repository;

import com.example.manageequipment.model.Category;
import com.example.manageequipment.model.Equipment;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import org.springframework.data.domain.Pageable;

import java.util.List;

@Repository
public interface EquipmentRepository extends JpaRepository<Equipment, Long> {
//    List<Equipment> findAllByName(String name);

//    @Query("SELECT e from Equipment e where e.name like %:name%")
    List<Equipment> findByNameContaining(String name);

    List<Equipment> findByOwnerId(int ownerId);

    List<Equipment> findByType(Category category);

    @Query(value = "SELECT * FROM equipment e WHERE owner_id = :ownerId and NAME LIKE :name%",
            countQuery = "SELECT count(*) FROM equipment e WHERE owner_id = :ownerId and NAME LIKE :name%",
            nativeQuery = true)
    Page<Equipment> findByOwnerId(String name, Long ownerId, Pageable pageable);
}
