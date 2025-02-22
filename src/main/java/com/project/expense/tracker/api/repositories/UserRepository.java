package com.project.expense.tracker.api.repositories;

import com.project.expense.tracker.api.exception.EtAuthException;
import com.project.expense.tracker.api.domain.User;


public interface UserRepository {
Integer create(String firstname, String lastname, String email, String password ) throws EtAuthException;


User findByEmailAndPassword(String email, String password) throws EtAuthException;


Integer getCountByEmail(String email);


User findById(Integer userId);



}
