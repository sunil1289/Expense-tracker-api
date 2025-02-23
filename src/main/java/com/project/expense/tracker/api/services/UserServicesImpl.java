package com.project.expense.tracker.api.services;

import com.project.expense.tracker.api.domain.User;
import com.project.expense.tracker.api.exception.EtAuthException;
import com.project.expense.tracker.api.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

@Service
public class UserServicesImpl implements UserServices {


    @Autowired
    UserRepository userRepository;

    @Override
    public User validateUser(String email, String password) throws EtAuthException {
        if(email != null ) {
            email = email.toLowerCase();
        }
        return userRepository.findByEmailAndPassword(email, password);

    }

    @Override
    public User registerUser(String firstname, String lastname, String email, String password) throws EtAuthException {
        Pattern pattern = Pattern.compile("^(.+)@(.+)$");
        if(email != null ) {
            email = email.toLowerCase();
        }
        if(!pattern.matcher(email).matches()){
           throw new EtAuthException("Invalid email format");
           }
        Integer count = userRepository.getCountByEmail(email);

        if(count > 0)
             throw  new EtAuthException("Email already in use" );
        Integer userId = userRepository.create(firstname,lastname,email,password);
    return  userRepository.findById(userId);
    }
}
