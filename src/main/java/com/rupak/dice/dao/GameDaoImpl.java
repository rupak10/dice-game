package com.rupak.dice.dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Component;
import com.rupak.dice.appdata.ApplicationDB;
import com.rupak.dice.model.CurrentScoreVo;
import com.rupak.dice.model.Player;
import com.rupak.dice.util.ScoreComparator;

@Component
public class GameDaoImpl implements GameDao{

	@Override
	public void initializePlayers() {
		Map<Integer, Player> playerMap = ApplicationDB.getPlayers();
		for (Map.Entry<Integer, Player> entry : playerMap.entrySet()) {
			Player player = entry.getValue();
			player.setGameEnrolledStatus(false);
			player.setTotalScore(0);
			ApplicationDB.getPlayers().put(player.getId(), player);
		}
	}

	@Override
	public List<CurrentScoreVo> getCurrentScores() {
		List<CurrentScoreVo> currentScores = new ArrayList<>();
		Map<Integer, Player> playerMap = ApplicationDB.getPlayers();
		for (Map.Entry<Integer, Player> entry : playerMap.entrySet()) {
			Player player = entry.getValue();
			CurrentScoreVo currentScoreVo = new CurrentScoreVo(player.getName(), player.getTotalScore());
			currentScores.add(currentScoreVo);
		}
		
		//sort the players based on the highest score
		Collections.sort(currentScores, new ScoreComparator());
		
		return currentScores;
	}

}
