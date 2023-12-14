package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.CategoryModel;
import com.example.demo.service.CategoryService;

@RestController
@RequestMapping
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/category/getAllCategories")
    public ResponseEntity<List<CategoryModel>> getAllCategories() {
        List<CategoryModel> categories = categoryService.getAllCategories();
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    @GetMapping("/category/getCategoryById/{id}")
    public ResponseEntity<CategoryModel> getCategoryById(@PathVariable Long id) {
        CategoryModel category = categoryService.getCategoryById(id);
        if (category != null) {
            return new ResponseEntity<>(category, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/category/addCategory")
    public ResponseEntity<CategoryModel> addCategory(@RequestBody CategoryModel category) {
        CategoryModel newCategory = categoryService.addCategory(category);
        return new ResponseEntity<>(newCategory, HttpStatus.CREATED);
    }

    @PutMapping("/category/updateCategory/{id}")
    public ResponseEntity<CategoryModel> updateCategory(@PathVariable Long id, @RequestBody CategoryModel newCategory) {
        CategoryModel updatedCategory = categoryService.updateCategory(id, newCategory);
        if (updatedCategory != null) {
            return new ResponseEntity<>(updatedCategory, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/category/deleteCategory/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable Long id) {
        boolean result = categoryService.deleteCategory(id);
        if (result) {
            return new ResponseEntity<>("Category deleted successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Category not found", HttpStatus.NOT_FOUND);
        }
    }
}
