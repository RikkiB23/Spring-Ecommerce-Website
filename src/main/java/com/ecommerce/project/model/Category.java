package com.ecommerce.project.model;

public class Category {

    private Long categoryId;
    private String categoryName;

    //Constructor
    public Category(String categoryName, Long categoryId) {
        this.categoryName = categoryName;
        this.categoryId = categoryId;
    }

    //Getters and Setters
    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }



}
