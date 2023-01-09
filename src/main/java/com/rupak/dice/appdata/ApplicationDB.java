/**
 * @author : Rupak Kumar Das 
 * @contact : rupak.cse010@gmail.com
 */
package com.rupak.dice.appdata;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import com.rupak.dice.model.Player;

public class ApplicationDB {
	
	public static Integer PLAYER_ID = 0;
	public static Integer TOTAL_PLAYER = 0;
	public static Integer WINING_SCORE = 25;
	public static Boolean IS_GAME_ALREADY_RUNNING = false;
	public static ConcurrentHashMap<Integer, Player> players = new ConcurrentHashMap<>();
	
	public static Integer getPlayerId() {
		PLAYER_ID += 1;
		TOTAL_PLAYER += 1;
		return PLAYER_ID;
	}
	
	public static Integer getTotalPlayer() {
		return TOTAL_PLAYER;
	}
	
	public static Map<Integer, Player> getPlayers() {
		return players;
	}

}
