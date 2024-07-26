package com.jap.task;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Category {

    //declare an attribute categoryName
    private String categoryName;

    public Category(String categoryName) {
        this.categoryName = categoryName;
    }


    public Category() {

    }
    //declare getter and setter and toString methods

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryName() {
        return this.categoryName;
    }


    @Override
    public String toString() {
        return "Category{" +
                "categoryName='" + categoryName + '\'' +
                '}';
    }
}
