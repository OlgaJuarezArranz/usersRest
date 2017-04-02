/**
 * simpleRestful
 */
package com.ojuarezarranz.services;

import java.util.List;

import com.ojuarezarranz.entities.Users;

public interface UsersService {
	
	public List<Users> findAllUsers();
	
	public Users findById(int id);
	
	public Users createUser (Users user);
	
	public Users updateUser (Users user);
	
	public void removeUser (int id);
	
	public boolean isUserExist (Users user);

}
