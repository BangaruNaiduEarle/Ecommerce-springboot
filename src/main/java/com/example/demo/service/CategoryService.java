package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.CategoryModel;
import com.example.demo.repo.CategoryRepo;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepo categoryRepo;
	
	public List<CategoryModel> getAllCategories() {
        return categoryRepo.findAll();
    }
	public CategoryModel getCategoryById(Long id) {
        return categoryRepo.findById(id).orElse(null);
    }

    public CategoryModel addCategory(CategoryModel category) {
        return categoryRepo.save(category);
    }

    public CategoryModel updateCategory(Long id, CategoryModel newCategory) {
        if (categoryRepo.existsById(id)) {
            newCategory.setId(id);
            return categoryRepo.save(newCategory);
        }
        return null; // Category not found
    }
    public boolean deleteCategory(Long id) {
        if (categoryRepo.existsById(id)) {
            categoryRepo.deleteById(id);
            return true;
        }
        return false; // Category not found
    }

}
