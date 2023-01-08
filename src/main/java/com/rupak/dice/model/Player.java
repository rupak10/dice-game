package com.rupak.dice.model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(description = "Game Player Info")
@Data
public class Player {
	
	@ApiModelProperty(notes = "Unique id for each player")
	private Integer id;
	
	@ApiModelProperty(notes = "Player name")
	private String name;
	
	@ApiModelProperty(notes = "Player age")
	private Integer age;
	
	@ApiModelProperty(notes = "Player staus in game - active or inactive")
	private Boolean gameEnrolledStatus;
	
	@ApiModelProperty(notes = "Total score of a player in the game")
	private Integer totalScore;
	
	public Player() {
		super();
		this.gameEnrolledStatus = false;
		this.totalScore = 0;
	}
}
