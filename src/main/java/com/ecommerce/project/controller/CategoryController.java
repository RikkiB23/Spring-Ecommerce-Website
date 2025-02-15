package com.ecommerce.project.controller;

import com.ecommerce.project.model.Category;
import com.ecommerce.project.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api") //we know that all the mappings will begin with /api so we can add the RequestMapping at the Class level
public class CategoryController {

    //@Autowired --> This would remove the need for the constructor injection, but this is not the approved method
    private CategoryService categoryService;

    //Constructor injection (this is more secure than @Autowired)
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    //@GetMapping("/api/public/categories") this has /api in front, but we have added @RequestMapping at class level so this will be removed everywhere at Method level
    @GetMapping("/public/categories")
    //@RequestMapping(value = "/public/categories", method = RequestMethod.GET) //this does the same as the @GetMapping
    public ResponseEntity<List<Category>> getAllCategories() {
        List<Category> allCategories = categoryService.getAllCategories();
        return new ResponseEntity<>(allCategories, HttpStatus.OK);
    }

    @PostMapping("/public/categories")
    public ResponseEntity<String> createCategory(@RequestBody Category category) {
        categoryService.createCategory(category);
        return new ResponseEntity<>("Category Added Successfully", HttpStatus.CREATED);
    }

    @DeleteMapping("/admin/categories/{categoryID}")
    public ResponseEntity<String> deleteCategory(@PathVariable Long categoryID) { //we're using ResponseEntity to provide the correct status codes
        try {
            String status = categoryService.deleteCategory(categoryID);
            return new ResponseEntity<>(status, HttpStatus.OK);
        } catch (ResponseStatusException e) {
            return new ResponseEntity<>(e.getReason(), e.getStatusCode());
        }
    }

    @PutMapping("/public/categories/{categoryID}")
    public ResponseEntity<String> updateCategory(@RequestBody Category category, @PathVariable Long categoryID) {
        try {
            Category savedCategory = categoryService.updateCategory(category, categoryID);
            return new ResponseEntity<>("Category with Category ID: " + categoryID + " updated", HttpStatus.OK);
        } catch (ResponseStatusException e) {
            return new ResponseEntity<>(e.getReason(), e.getStatusCode());
        }
    }

}
