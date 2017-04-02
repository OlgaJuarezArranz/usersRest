package com.ojuarezarranz.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import org.joda.time.LocalDate;


@Entity
@Table(name = "users")
public class Users  implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@NotNull
	@Column(name = "user_id")
	private int userId;
	
	@NotNull
	@Column(name = "user_name")
	private String userName;
	
	@NotNull
	@Column(name= "user_birthdate")
	private LocalDate userBirthdate;

	/**
	 * Constructor 
	 */
	public Users() {
	}
	
	
	//Setters and getters
	public int getUserId(){
		return this.userId;
	}
	
	public void setUserId(int userId){
		this.userId = userId;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public LocalDate getUserBirthdate() {
		return this.userBirthdate;
	}

	public void setUserBirthdate(LocalDate userBirthdate) {
		this.userBirthdate = userBirthdate;
	}

}
