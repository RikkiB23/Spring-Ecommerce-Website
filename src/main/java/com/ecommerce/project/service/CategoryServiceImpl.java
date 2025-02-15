package com.ecommerce.project.service;

import com.ecommerce.project.model.Category;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService{

    private List<Category> categories = new ArrayList<>();
    private Long nextId = 1L;

    @Override
    public List<Category> getAllCategories() {
        return categories;
    }

    @Override
    public void createCategory(Category category) {
        category.setCategoryId(nextId++);
        categories.add(category);
    }

    @Override
    public String deleteCategory(Long categoryID) {
        Category category = categories.stream()
                .filter(c -> c.getCategoryId().equals(categoryID)) //for every category, we're checking if the category ID matches the ID we want
                .findFirst() //if it matches, get the first instance of the matching ID and assign it to the Category object
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Resource not found")); //this will throw a 404 NOT FOUND exception if the category ID doesn't exist

        categories.remove(category);
        return "Category with Category ID: " + categoryID + " has been removed.";
    }

    @Override
    public Category updateCategory(Category category, Long categoryID) {
        Optional<Category> optionalCategory = categories.stream()
                .filter(c -> c.getCategoryId().equals(categoryID))
                .findFirst();
        //we do the above because the category might not exist

        if (optionalCategory.isPresent()) {
            Category existingCategory = optionalCategory.get();
            existingCategory.setCategoryName(category.getCategoryName());
            return existingCategory;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found");
        }
    }
}
