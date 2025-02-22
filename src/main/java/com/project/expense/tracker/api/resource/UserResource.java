package com.project.expense.tracker.api.resource;

import com.project.expense.tracker.api.Constants;
import com.project.expense.tracker.api.services.UserServices;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.project.expense.tracker.api.domain.User;

import javax.swing.*;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserResource {

    @Autowired
    UserServices userServices;


    @PostMapping("/login")
    public ResponseEntity<Map<String,String>> loginUser(@RequestBody Map<String, Object> userMap) {
        String email = (String) userMap.get("email");
        String password = (String) userMap.get("password");
       User user = userServices.validateUser(email,password);
       return new ResponseEntity<>(generateJWTToken(user), HttpStatus.OK);

    }
    @PostMapping("/register")
    public ResponseEntity<Map<String,String>> registerUser(@RequestBody Map<String, Object> userMap){
        String firstname = (String) userMap.get("firstname");
        String lastname = (String) userMap.get("lastname");

        String email = (String) userMap.get("email");
        String password = (String) userMap.get("password");

        User user =userServices.registerUser(firstname,lastname,email,password);

        return new ResponseEntity<>(generateJWTToken(user), HttpStatus.OK);

    }

    private  Map<String,String> generateJWTToken(User user){
       long timestamp = System.currentTimeMillis();
       String token = Jwts.builder().signWith(SignatureAlgorithm.HS256, Constants.API_SECRET_KEY)
               .setIssuedAt(new Date(timestamp))
               .setExpiration(new Date(timestamp + Constants.TOKEN_VALIDXITY))
               .claim("userId",user.getUserId())
               .claim( "email",user.getEmail())
               .claim( "firstName",user.getFirstName())
               .claim( "lastName",user.getLastName())
               .compact();
       Map<String,String> map = new HashMap<>();
        map.put("token",token);
        return map;

    }

}
