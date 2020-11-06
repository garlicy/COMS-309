package com.ccd.app.categories;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ccd.app.model.Categories;
import com.ccd.app.gamedata.*;
import com.ccd.app.model.GameData;


@Controller
@RequestMapping(path="/categories")
public class CategoriesController {

	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private GameDataRepository gameDataRepo;
	
	
	@GetMapping(path="/get")
	public @ResponseBody JSONObject getCategories() {
		JSONObject json = new JSONObject();
		List<Categories> cats = categoryRepository.findAll();
		
		json.put("total", cats.size());
		for(int i = 0; i < cats.size(); ++i) {
			json.put("Name", cats.get(i).getName());
		}
		
		return json;
	}
	
	@GetMapping(path="/games")
	public @ResponseBody JSONObject getGamesInCategory(@RequestParam String CategoryName) {
		JSONObject ja = new JSONObject();
		Categories cat = categoryRepository.findByCategoryName(CategoryName);
		ArrayList<String> games = cat.getAllGames();
		
		ja.put("Total", cat.getNumGamesInCategory());
		
		for(int i = 0; i < cat.getNumGamesInCategory(); ++i) {
			GameData g = gameDataRepo.findBygameName(games.get(i));
			ja.put("Name", g.getGameName());
			ja.put("Creator", g.getCreator());
			ja.put("IconID", g.getIconID());
		}
		
		return ja;
	}
	
}
