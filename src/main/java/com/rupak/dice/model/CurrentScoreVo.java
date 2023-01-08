package com.rupak.dice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class CurrentScoreVo {
	private String name;
	private Integer currentScore;
}
