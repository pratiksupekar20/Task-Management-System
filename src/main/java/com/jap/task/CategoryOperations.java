package com.jap.task;

import java.util.*;

/**
 * The CategoryOperations class provides operations related to managing categories.
 */
public class CategoryOperations {

    // Declare the categories attribute
    // This attribute will hold all the categories
    List<Category> categories;

    public CategoryOperations() {
        categories = new ArrayList<Category>();
        // Initialize the categories list
    }

    public boolean addCategory(String categoryName) {

        for (Category category : categories) {
            if (category.getCategoryName().equals(categoryName)) {
                return false;
            }
        }
        categories.add(new Category(categoryName));
        return true;
    }

    public Category findCategory(String categoryName) {
        if (categories.isEmpty()) {
            return null;
        }
        for (Category category : categories) {
            if (category.getCategoryName().equals(categoryName)) {
                return category;
            }
        }
        return null;
    }

    public List<Category> listAllCategories() {
        // Display the categories and return the same


        return categories;


    }

    public static Category getCategoryByName(Map<Category, List<Task>> categoryTaskMap, String categoryName) {
        for (Category category : categoryTaskMap.keySet()) {
            if (category.getCategoryName().equals(categoryName)) {
                return category;
            }
        }
        return null;
    }
}
