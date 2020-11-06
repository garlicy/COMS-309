package com.ccd.app.model;

import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table (name = "categories")

public class Categories {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String categoryName;
	
	private ArrayList<String> gamesInCategory;
	
	
	
	
	public int getId() {
		return id;
	}
	
	public void setName(String name) {
		categoryName = name;
	}
	
	public String getName() {
		return categoryName;
	}
	
	public void addGame(String gameName) {
		gamesInCategory.add(gameName);
	}
	
	public String removeGame(String gameName) {
		for(int i = 0; i < gamesInCategory.size(); ++i) {
			if(gamesInCategory.get(i).equals(gameName)) {
				String removed = gamesInCategory.remove(i);
				return removed;
			}
		}
		return "Game Not Found";
	}
	
	public ArrayList<String> getAllGames() {
		return gamesInCategory;
	}
	
	public int getNumGamesInCategory() {
		return gamesInCategory.size();
	}
	
	
	
	
	
	
}
