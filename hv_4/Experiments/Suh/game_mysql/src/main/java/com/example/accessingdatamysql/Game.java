package com.example.accessingdatamysql;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity // This tells Hibernate to make a table out of this class
public class Game {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;

	private String gamename;
	
	private Integer numofpeople;
	
	private String description;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getGamename() {
		return gamename;
	}

	public void setGamename(String gamename) {
		this.gamename = gamename;
	}
	
	public Integer getNumofpeople() {
		return numofpeople;
	}
	
	public void setNumofpeople(Integer numofpeople) {
		this.numofpeople = numofpeople;
	}

	public String getDescription(){
		return description;
	}
	
	public void setDescription(String description) {
		this.description=description;
	}
}
