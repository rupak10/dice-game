package com.rupak.dice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rupak.dice.dao.GameDao;
import com.rupak.dice.model.CurrentScoreVo;

@Service
public class GameServiceImpl implements GameService {
	
	@Autowired
	private GameDao gameDao;

	@Override
	public void initializePlayers() {
		gameDao.initializePlayers();
	}

	@Override
	public List<CurrentScoreVo> getCurrentScores() {
		return gameDao.getCurrentScores();
	}

}
