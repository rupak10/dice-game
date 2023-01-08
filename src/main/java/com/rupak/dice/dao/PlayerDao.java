package com.rupak.dice.dao;
import java.util.List;
import java.util.Map;

import com.rupak.dice.model.Player;

public interface PlayerDao {
	public Map<String, Object> addPlayer(Player player);
	public List<Player> getPlayers();
}
