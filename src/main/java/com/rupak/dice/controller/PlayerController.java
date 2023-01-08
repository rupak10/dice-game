/**
 * @author : Rupak Kumar Das 
 * @contact : rupak.cse010@gmail.com
 */
package com.rupak.dice.controller;

import java.util.LinkedHashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.rupak.dice.model.Player;
import com.rupak.dice.service.PlayerService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/v1/players")
public class PlayerController {
	
	private Logger log = LoggerFactory.getLogger(PlayerController.class);
	
	@Autowired
	private PlayerService playerService;
	
	@PostMapping("/add")
	@ApiOperation(value = "add a new player to the game", notes = "returns newly added player")
	public Map<String, Object> addPlayer(@RequestBody Player player) {
		log.info("Entering addPlayer() method of PlayerController");
		log.info("player : "+player);
		Map<String, Object> result = new LinkedHashMap<>();
		try {
			result = playerService.addPlayer(player);
		} catch (Exception e) {
			e.printStackTrace();
			result.put("p_out", "0");
			result.put("p_error_code", "PLAYER-ADD-001");
			result.put("p_error_message", "Processing Error !");
		}
		log.info("Exiting addPlayer() method of PlayerController");
		return result;
	}
	
	
	@GetMapping()
	@ApiOperation(value = "fetch player list", notes = "returns player list")
	public Map<String, Object> getPlayers() {
		log.info("Entering getAllPlayers() method of PlayerController");
		Map<String, Object> result = new LinkedHashMap<>();
		try {
			result = playerService.getPlayers();
		} catch (Exception e) {
			e.printStackTrace();
			result.put("p_out", "0");
			result.put("p_error_code", "PLAYER-FETCH-ERR-001");
			result.put("p_error_message", "Processing Error !");
		}
		log.info("Exiting getAllPlayers() method of PlayerController");
		return result;
	}

}
