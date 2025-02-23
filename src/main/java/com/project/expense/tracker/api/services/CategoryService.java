package com.project.expense.tracker.api.services;

import com.project.expense.tracker.api.domain.Category;
import com.project.expense.tracker.api.exception.EtBadRequestException;
import com.project.expense.tracker.api.exception.EtResourceNotFoundException;

import java.util.List;

public interface CategoryService {
    List<Category> fetchAllCategories(Integer userId);
    Category fetchCategoryById(Integer userId,Integer categoryId) throws EtResourceNotFoundException;
    Category addCategory(Integer userId,String title, String description) throws EtBadRequestException;
    void  updateCategory (Integer userId,Integer categoryId,Category category) throws EtBadRequestException;
    void  removeCategoryWithAllTransaction (Integer userId,Integer categoryId) throws EtResourceNotFoundException;
}
