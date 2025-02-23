package com.project.expense.tracker.api.repositories;

import com.project.expense.tracker.api.domain.Category;
import com.project.expense.tracker.api.exception.EtBadRequestException;
import com.project.expense.tracker.api.exception.EtResourceNotFoundException;

import java.util.List;

public interface CategoryRepository {

    List<Category> findAll(Integer userId) throws EtResourceNotFoundException;
    Category findById(Integer userId,Integer categoryId) throws EtResourceNotFoundException;
 Integer create(Integer userId,String title,String description) throws EtBadRequestException;
 void update(Integer userId,Integer categoryId,Category category) throws EtBadRequestException;
 void removeById(Integer userId,Integer categoryId);

    void remove(Integer userId, Integer categoryId);
}

