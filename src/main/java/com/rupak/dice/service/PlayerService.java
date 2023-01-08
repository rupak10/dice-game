package com.rupak.dice.service;

import java.util.Map;

import com.rupak.dice.model.Player;

public interface PlayerService {
	public Map<String, Object> addPlayer(Player player);
	public Map<String, Object> getPlayers();
}
