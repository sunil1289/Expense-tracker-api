package com.project.expense.tracker.api.services;

import com.project.expense.tracker.api.domain.User;
import com.project.expense.tracker.api.exception.EtAuthException;


public interface UserServices {


    User validateUser(String email, String password) throws EtAuthException;


    User registerUser(String firstname, String lastname, String email, String password ) throws EtAuthException;
}
