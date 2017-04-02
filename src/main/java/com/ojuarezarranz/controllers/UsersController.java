package com.ojuarezarranz.controllers;

import java.util.List;

import org.hibernate.type.CustomType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.ojuarezarranz.services.UsersService;
import com.ojuarezarranz.entities.Users;


@RestController
@RequestMapping("/api")
public class UsersController {

    @Autowired
    UsersService usersService;
    
    @RequestMapping(value = "/user/getall/", method = RequestMethod.GET)
    public ResponseEntity<List<Users>> listAllUsers() {
    	
        List<Users> usersList = (List<Users>) usersService.findAllUsers();
        if (usersList.isEmpty()) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
         } 
         return new ResponseEntity<List<Users>>(usersList, HttpStatus.OK);
    }
    
    @SuppressWarnings("unchecked")
	@RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getUser(@PathVariable("id") int id) {
      
        Users user = usersService.findById(id);
        if (user == null) {
            return new  ResponseEntity(HttpStatus.NOT_FOUND);     
        }
        return new ResponseEntity<Users>(user, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/user/create/", method = RequestMethod.POST)
    public ResponseEntity<?> createUser(@RequestBody Users user, UriComponentsBuilder ucBuilder) {
        
       if (usersService.isUserExist(user)) {
          
            return new ResponseEntity(HttpStatus.CONFLICT);
        }
        usersService.createUser(user);
 
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/api/user/{id}").buildAndExpand(user.getUserId()).toUri());
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }
    
    @RequestMapping(value = "/user/update/", method = RequestMethod.POST)
    public ResponseEntity<?> updateUser(@RequestBody Users user, UriComponentsBuilder ucBuilder) {
         
    	  
    	 Users currentUser = usersService.findById(user.getUserId());
         if (currentUser == null) {
        	  return new  ResponseEntity(HttpStatus.NOT_FOUND);     
        }
 
        currentUser.setUserName(user.getUserName());
        currentUser.setUserBirthdate(user.getUserBirthdate());
      
         usersService.updateUser(currentUser);
         
         HttpHeaders headers = new HttpHeaders();
         headers.setLocation(ucBuilder.path("/api/user/update").buildAndExpand(user.getUserId()).toUri());
         return new ResponseEntity<String>(headers, HttpStatus.OK);

    }
    
    @RequestMapping(value = "/user/remove/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> removeUser(@PathVariable("id") int id) {
        
        Users user = usersService.findById(id);
        if (user == null) {
        	return new  ResponseEntity(HttpStatus.NOT_FOUND);     
         }
        usersService.removeUser(id);
        return new ResponseEntity<Users>(HttpStatus.NO_CONTENT);
    }
 
}
