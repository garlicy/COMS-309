package com.ccd.app.model;

import java.util.ArrayList;
import java.util.List;

import com.ccd.app.model.Phases;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Entity Model of GameData
 * @author jsuh_mac
 *
 */
public class GameDataModel {
	
	private Integer gameData_id;
	private String instructions;
	private String gameName;
	private String numPlayers;
	private String numDecks;
	private Integer totalRounds;
	private Integer pointsGoal;
	private List<String> playAreaNames;
	private List<String> playAreaVisibility;
//	private String playAreaNames;
//	private String playAreaVisibility;
//	@OneToMany(mappedBy="gameData")
    private List<PhasesModel> Phases = new ArrayList<PhasesModel>();

	//private ArrayList<Phases> Phases;
	
	//used for game information screens
//	private String overview;
//	private String rules;
//	private String scoring;
//	private String extraInfo;
//	private String creator;
//	private int iconID;
//	
//	public void setCreator(String name) {
//		creator = name;
//	}
//	public String getCreator() {
//		return creator;
//	}
//	public void setIconID(int iconId) {
//		iconID = iconId;
//	}
//	public int getIconID() {
//		return iconID;
//	}
//	public void setOverview(String paragraph) {
//		overview = paragraph;
//	}
//	public String getOverview() {
//		return overview;
//	}
//	public void setRules(String para) {
//		rules = para;
//	}
//	public String getRules() {
//		return rules;
//	}
//	public void setScoring(String para) {
//		scoring = para;
//	}
//	public String getScoring() {
//		return scoring;
//	}
//	public void setExtraInfo(String para) {
//		extraInfo = para;
//	}
//	public String getExtraInfo() {
//		return extraInfo;
//	}
//	
    public List<PhasesModel> getPhases() {
		return Phases;
	}
	public void setPhases(List<PhasesModel> phases) {
		Phases = phases;
	}
	public String getInstructions() {
		return instructions;
	}
	
	public void setInstructions(String instructions) {
		this.instructions = instructions;
	}

	public Integer getGameData_id() {
		return gameData_id;
	}
	public void setGameData_id(Integer gameData_id) {
		this.gameData_id = gameData_id;
	}
	public String getGameName() {
		return gameName;
	}
	public void setGameName(String gameName) {
		this.gameName = gameName;
	}
	public String getNumPlayers() {
		return numPlayers;
	}
	public void setNumPlayers(String numPlayers) {
		this.numPlayers = numPlayers;
	}
	public String getNumDecks() {
		return numDecks;
	}
	public void setNumDecks(String numDecks) {
		this.numDecks = numDecks;
	}
	public Integer getTotalRounds() {
		return totalRounds;
	}
	public void setTotalRounds(Integer totalRounds) {
		this.totalRounds = totalRounds;
	}
	public Integer getPointsGoal() {
		return pointsGoal;
	}
	public void setPointsGoal(Integer pointsGoal) {
		this.pointsGoal = pointsGoal;
	}
	
//	public String getPlayAreaNames() {
//		return playAreaNames;
//	}
//	public void setPlayAreaNames(String playAreaNames) {
//		this.playAreaNames = playAreaNames;
//	}
//	public String getPlayAreaVisibility() {
//		return playAreaVisibility;
//	}
//	public void setPlayAreaVisibility(String playAreaVisibility) {
//		this.playAreaVisibility = playAreaVisibility;
//	}
	public List<String> getPlayAreaNames() {
		return playAreaNames;
	}
	public void setPlayAreaNames(List<String> playAreaNames) {
		this.playAreaNames = playAreaNames;
	}
	public List<String> getPlayAreaVisibility() {
		return playAreaVisibility;
	}
	public void setPlayAreaVisibility(List<String> playAreaVisibility) {
		this.playAreaVisibility = playAreaVisibility;
	}
//	public List<Phases> getPhases() {
//		return Phases;
//	}
//	public void setPhases(List<Phases> phases) {
//		this.Phases = phases;
//	}
	
}