package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.CategoryModel;
@Repository
public interface CategoryRepo extends JpaRepository<CategoryModel, Long> {

}
