package com.example.manageequipment.repository;

import com.example.manageequipment.model.Category;
import com.example.manageequipment.dto.CategoryDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    @Query("SELECT new com.example.manageequipment.dto.CategoryDto(c.id, c.name) from Category c")
    List<CategoryDto> getNameOfCategory();
}
