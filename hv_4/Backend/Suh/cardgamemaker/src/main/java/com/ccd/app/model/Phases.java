package com.ccd.app.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Phases")
public class Phases {
	
	@ManyToOne
	@JoinColumn(name="gameData_id", nullable=false)
	private GameData gameData;
//	private Integer gameData_id;
	
	@Id
	private Integer phaseNumber;
	
	private String first;
	private String second;
	private String sender;
	private String receiver;
	private String chosenBy;
	private String pointGetter;
	private String pointVal;
	private String comparators;
	
	public GameData getGameData() {
		return gameData;
	}
	public void setGameData(GameData gameData) {
		this.gameData = gameData;
	}
//	public Integer getGameData_id() {
//		return gameData_id;
//	}
//	public void setGameData_id(Integer gameData_id) {
//		this.gameData_id = gameData_id;
//	}
	public Integer getPhaseNumber() {
		return phaseNumber;
	}
	
	public void setPhaseNumber(Integer phaseNumber) {
		this.phaseNumber = phaseNumber;
	}
	
	public String getFirst() {
		return first;
	}
	public void setFirst(String first) {
		this.first = first;
	}
	public String getSecond() {
		return second;
	}
	public void setSecond(String second) {
		this.second = second;
	}
	
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public String getReceiver() {
		return receiver;
	}
	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}
	public String getChosenBy() {
		return chosenBy;
	}
	public void setChosenBy(String chosenBy) {
		this.chosenBy = chosenBy;
	}
	public String getPointGetter() {
		return pointGetter;
	}
	public void setPointGetter(String pointGetter) {
		this.pointGetter = pointGetter;
	}
	public String getPointVal() {
		return pointVal;
	}
	public void setPointVal(String pointVal) {
		this.pointVal = pointVal;
	}
	public String getComparators() {
		return comparators;
	}
	public void setComparators(String comparators) {
		this.comparators = comparators;
	}
}
