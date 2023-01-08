package com.rupak.dice.dao;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.rupak.dice.appdata.ApplicationDB;
import com.rupak.dice.model.Player;

@Component
public class PlayerDaoImpl implements PlayerDao {

	@Override
	public Map<String, Object> addPlayer(Player player) {
		Map<String, Object> result = new LinkedHashMap<>();
		try {
			Player newPlayer = new Player();
			newPlayer.setId(ApplicationDB.getPlayerId());
			newPlayer.setName(player.getName());
			newPlayer.setAge(player.getAge());
			
			ApplicationDB.getPlayers().put(newPlayer.getId(), newPlayer);
			
			result.put("p_out", "1");
			result.put("p_error_code", "SUCCESS-000");
			result.put("p_error_message", "Player Added to Game Successfully");
			result.put("playerInfo", newPlayer);
		} catch (Exception e) {
			e.printStackTrace();
			result.put("p_out", "0");
			result.put("p_error_code", "PLAYER-ADD-004");
			result.put("p_error_message", "Processing Error !");
		}
		
		return result;
	}

	
	@Override
	public List<Player> getPlayers() {
		List<Player> playerList = new ArrayList<>();
		try {
			Map<Integer, Player> playerMap = ApplicationDB.getPlayers();
			for (Map.Entry<Integer, Player> entry : playerMap.entrySet()) {
			    playerList.add(entry.getValue());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return playerList;
	}

}
