package com.rupak.dice.dao;

import java.util.List;

import com.rupak.dice.model.CurrentScoreVo;

public interface GameDao {
	public void initializePlayers();
	public List<CurrentScoreVo> getCurrentScores();
}
