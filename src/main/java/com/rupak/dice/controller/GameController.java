/**
 * @author : Rupak Kumar Das 
 * @contact : rupak.cse010@gmail.com
 */
package com.rupak.dice.controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import com.rupak.dice.appdata.ApplicationDB;
import com.rupak.dice.model.CurrentScoreVo;
import com.rupak.dice.service.GameService;
import com.rupak.dice.util.GameHandler;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/v1/game")
public class GameController {
	
	private Logger log = LoggerFactory.getLogger(GameController.class);
	private static final String CURRENT_SCORE_URL = "http://localhost:8080/api/v1/game/current-scores";
	
	@Autowired
	private GameService gameService;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@GetMapping("/start")
	@ApiOperation(value = "start the game", notes = "game starts")
	public Map<String, Object> startGame() {
		log.info("Entering startGame() method of GameController");
		Map<String, Object> result = new LinkedHashMap<>();
		try {
			if(ApplicationDB.getPlayers().size() < 2) {
				result.put("p_out", "0");
				result.put("p_error_code", "GAME-START-ERR-001");
				result.put("p_error_message", "Minimum 2 players needed to start the game !");
				log.info("Exiting startGame() method of GameController");
				return result;
			}
			
			if(!ApplicationDB.IS_GAME_ALREADY_RUNNING) {
				gameService.initializePlayers();
				GameHandler gameHandler = new GameHandler(restTemplate);
				gameHandler.start();
				
				result.put("p_out", "1");
				result.put("p_error_code", "SUCCESS-000");
				result.put("p_error_message", "Dice game started successfully");
				result.put("p_current_score_url", CURRENT_SCORE_URL);
				
				log.info("Exiting startGame() method of GameController");
				return result;
			}
			else {
				result.put("p_out", "0");
				result.put("p_error_code", "GAME-START-ERR-002");
				result.put("p_error_message", "One game is already running");
				log.info("Exiting startGame() method of GameController");
				return result;
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.put("p_out", "0");
			result.put("p_error_code", "GAME-START-ERR-003");
			result.put("p_error_message", "Processing Error !");
			log.info("Exiting startGame() method of GameController");
			return result;
		}
	}
	
	
	@GetMapping("/current-scores")
	@ApiOperation(value = "fetch current socres of all players", notes = "current socres of all players")
	public Map<String, Object> getCurrentScores() {
		log.info("Entering getCurrentScores() method of GameController");
		Map<String, Object> result = new LinkedHashMap<>();
		try {
			List<CurrentScoreVo> currentScores = gameService.getCurrentScores();
			
			result.put("p_out", "1");
			result.put("p_error_code", "SUCCESS-000");
			result.put("p_error_message", "Current scores fetched successfully");
			result.put("p_current_scores", currentScores);
			log.info("Exiting startGame() method of GameController");
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			result.put("p_out", "0");
			result.put("p_error_code", "GAME-SCORE-ERR-001");
			result.put("p_error_message", "Processing Error !");
			log.info("Exiting getCurrentScores() method of GameController");
			return result;
		}
	}

}
