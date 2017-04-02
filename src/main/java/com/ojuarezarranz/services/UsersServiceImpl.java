package com.ojuarezarranz.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ojuarezarranz.repositories.UsersRepository;
import com.ojuarezarranz.entities.Users;

@Service("usersService")
@Transactional
public class UsersServiceImpl implements UsersService {

	@Autowired
	private UsersRepository usersRepository;

	@Override
	public List<Users> findAllUsers() {
		return usersRepository.findAllUsers();
	}

	@Override
	public Users findById(int id) {
		return usersRepository.findById(id);
	}

	@Override
	public Users createUser(Users user) {
		return usersRepository.createUser(user);
	}

	@Override
	public Users updateUser(Users user) {
		return usersRepository.updateUser(user);
	}

	@Override
	public void removeUser(int id) {
		usersRepository.removeUser(id);
	}

	@Override
	public boolean isUserExist(Users user) {
		boolean isUExist = false;
		List<Users> usersList = findAllUsers();
		int i = 0;
		if (usersList.size() > 0) {
			do {
				Users u = usersList.get(i);
				if (u.getUserId() == user.getUserId() && u.getUserName().equalsIgnoreCase(user.getUserName())) {
					int yearDif = u.getUserBirthdate().getYear() - user.getUserBirthdate().getYear();
					if (yearDif == 0) {
						int monthDif = u.getUserBirthdate().getMonthOfYear() - user.getUserBirthdate().getMonthOfYear();
						if (monthDif == 0) {
							int dayDif = u.getUserBirthdate().getDayOfMonth() - user.getUserBirthdate().getDayOfMonth();
							if (dayDif == 0) {
								isUExist = true;
							}
						}
					}
				}
				i++;
			} while (i < usersList.size() && !isUExist);
		}
		return isUExist;
	}

}
