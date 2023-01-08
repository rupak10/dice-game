package com.rupak.dice.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rupak.dice.appdata.ApplicationDB;
import com.rupak.dice.dao.PlayerDao;
import com.rupak.dice.model.Player;
import com.rupak.dice.util.CommonUtil;

@Service
public class PlayerServiceImpl implements PlayerService {
	
	@Autowired
	private PlayerDao playerDao;

	@Override
	public Map<String, Object> addPlayer(Player player) {
		Map<String, Object> result = new LinkedHashMap<>();
		try {
			if(CommonUtil.isValueNotNullAndEmpty(player.getName()) && CommonUtil.isValueNotNullAndEmpty(player.getAge()) && player.getAge() > 0) {
				if(ApplicationDB.getPlayers().size() <= 3) {
					result = playerDao.addPlayer(player);
				}
				else {
					result.put("p_out", "0");
					result.put("p_error_code", "PLAYER-ADD-ERR-002");
					result.put("p_error_message", "Maximum 4 can be added to the game");
				}
			}
			else {
				result.put("p_out", "0");
				result.put("p_error_code", "PLAYER-ADD-ERR-003");
				result.put("p_error_message", "Invalid Player Info Provided !");
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.put("p_out", "0");
			result.put("p_error_code", "PLAYER-ADD-ERR-004");
			result.put("p_error_message", "Processing Error !");
		}
		
		return result;
	}

	
	@Override
	public Map<String, Object> getPlayers() {
		Map<String, Object> result = new LinkedHashMap<>();
		try {
			List<Player> playerList = playerDao.getPlayers();
			result.put("p_out", "1");
			result.put("p_error_code", "SUCCESS-000");
			result.put("p_error_message", "Player List Fetched Successfully");
			result.put("playerList", playerList);
		} catch (Exception e) {
			e.printStackTrace();
			result.put("p_out", "0");
			result.put("p_error_code", "PLAYER-FETCH-ERR-002");
			result.put("p_error_message", "Processing Error !");
		}
		return result;
	}

}
