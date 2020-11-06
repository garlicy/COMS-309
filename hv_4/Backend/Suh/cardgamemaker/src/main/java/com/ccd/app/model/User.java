package com.ccd.app.model;

import java.util.ArrayList;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.ccd.app.model.Role;

@Entity // This tells Hibernate to make a table out of this class
@Table (name = "user")

/**
 * Entity Model of User
 * @author jsuh_mac
 *
 */
public class User {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;

	private String username;

	private String email;
	
	private String password;
	
	private String usericon;
	
	@OneToMany
	private Set<Role> role;
	
	private ArrayList<String> friends;
	
//	private String passwordConfirm;
	
//	private UserRepository userRepository;

	public User() {
		
	}
	
	/**
	 * Mockito test Model
	 * @param id
	 * @param username
	 * @param password
	 * @param email
	 * @param usericon
	 */
	public User(int id, String username, String password, String email, String usericon) {
		this.id = id;
		this.username = username;
		this.email = email;
		this.password = password;
		this.usericon = usericon;
	}

	/**
	 * get id
	 * @return id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * set id
	 * @param id set to this id
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * get username
	 * @return username
	 */
	public String getUsername() {
		return username;
	}

/**
 * set username
 * @param username set to this username
 */
	public void setUsername(String username) {
		this.username = username;
	}
	
	/**
	 * get password
	 * @return password
	 */
	public String getPassword() {
		return password;
	}
	
	/**
	 * set password
	 * @param password set to this password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	
	/**
	 * get email
	 * @return email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * set email
	 * @param email set to this email
	 */
	
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * get usericon
	 * @return usericon
	 */
	public String getUsericon() {
		return usericon;
	}

	/**
	 * set usericon
	 * @param usericon set to this usericon
	 */
	public void setUsericon(String usericon) {
		this.usericon = usericon;
	}
	
	/**
	 * get Role
	 * @return role
	 */
	
	public Set<Role> getRole() {
		return role;
	}

	/**
	 * set Role
	 * @param role set to this Role
	 */
	public void setRole(Set<Role> role) {
		this.role = role;
	}
	
	/**
	 * Adds "user" to the friend list of the user being referenced
	 * @param user Name of the friend to be added
	 * add friend
	 * @param user to be friended
	 */
	public void addFriend(String user) {
		friends.add(user);
	}
	
	/**
	 * Returns an string arraylist of the user's friends
	 * @return String arraylist of user's friends
	 * get list of friends
	 * @return list of friends
	 */
	public ArrayList<String> getFriends() {
		return friends;
	}
	
	/**
	 * Removes "user" from the friend list of the user being referenced
	 * @param user remove this friend
	 */
	public void removeFriend(String user) {
		for(int i = 0; i < friends.size(); ++i) {
			if(friends.get(i).equals(user)) {
				friends.remove(i);
				break;
			}
		}
	}
	
	
	
	
	
	

//	public String getPasswordConfirm() {
//        return passwordConfirm;
//    }
//
//    public void setPasswordConfirm(String passwordConfirm) {
//        this.passwordConfirm = passwordConfirm;
//    }
}
