package com.ojuarezarranz.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ojuarezarranz.entities.Users;

public interface UsersRepository extends JpaRepository<Users, Integer>{
	
  public List<Users> findAllUsers();
	
	public Users findById(int id);
	
	public Users createUser (Users user);
	
	public Users updateUser (Users user);
	
	public void removeUser (int id);

}
