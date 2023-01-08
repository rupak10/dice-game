package com.rupak.dice.service;

import java.util.List;

import com.rupak.dice.model.CurrentScoreVo;

public interface GameService {
	public void initializePlayers();
	public List<CurrentScoreVo> getCurrentScores();
}
