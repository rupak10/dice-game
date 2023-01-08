/**
 * @author : Rupak Kumar Das 
 * @contact : rupak.cse010@gmail.com
 */
package com.rupak.dice.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;
import com.rupak.dice.appdata.ApplicationDB;
import com.rupak.dice.model.Player;
import com.rupak.dice.model.ScoreVo;

public class GameHandler extends Thread{
	
	private Logger log = LoggerFactory.getLogger(GameHandler.class);
	private static final String THROW_DICE_URL = "http://developer-test.hishab.io/api/v1/roll-dice";
	
	private RestTemplate restTemplate;
	
	public GameHandler(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}
	
	
	@Override
	public void run() {
		int currentPlayerId = 1;
		int totalPlayer = ApplicationDB.getTotalPlayer();
		Boolean gameRunFlag = true;
		
		while(gameRunFlag) {
			if(currentPlayerId > totalPlayer) {
				currentPlayerId = 1;
			}
			Player currentPlayer = ApplicationDB.getPlayers().get(currentPlayerId);
			Integer score = throwDice();
			if(score == 6) {
				if(currentPlayer.getGameEnrolledStatus()) {
					currentPlayer.setTotalScore(currentPlayer.getTotalScore() + score);
				}
				else {
					currentPlayer.setGameEnrolledStatus(true);
				}
				
				//update the player map
				ApplicationDB.getPlayers().put(currentPlayerId, currentPlayer);
				log.info("Player Name : " + currentPlayer.getName()
				+ ", Current Value of Dice : " + score + ", Total Score : " + currentPlayer.getTotalScore());
			}
			else if(score == 4) {
				if(currentPlayer.getGameEnrolledStatus() && currentPlayer.getTotalScore() == 0) {
					currentPlayer.setGameEnrolledStatus(false);
				}
				else if(currentPlayer.getGameEnrolledStatus() && currentPlayer.getTotalScore() > 0) {
					currentPlayer.setTotalScore(currentPlayer.getTotalScore() - score);
				}
				log.info("Player Name : " + currentPlayer.getName()
				+ ", Current Value of Dice : " + score + ", Total Score : " + currentPlayer.getTotalScore());
				
				//update the player map
				ApplicationDB.getPlayers().put(currentPlayerId, currentPlayer);
				
				currentPlayerId += 1;
			}
			else {
				if(currentPlayer.getGameEnrolledStatus()) {
					currentPlayer.setTotalScore(currentPlayer.getTotalScore() + score);
				}
				log.info("Player Name : " + currentPlayer.getName()
				+ ", Current Value of Dice : " + score + ", Total Score : " + currentPlayer.getTotalScore());
				
				//update the player map
				ApplicationDB.getPlayers().put(currentPlayerId, currentPlayer);
				
				currentPlayerId += 1;
			}
			
			if(currentPlayer.getTotalScore() >= ApplicationDB.WINING_SCORE) {
				log.info("Game Finished.");
				log.info("Winner : "+currentPlayer.getName());
				gameRunFlag = false;
			}
			
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
		
	}

	
	private Integer throwDice() {
		Integer score = 0;
		try {
			ScoreVo scoreVo = this.restTemplate.getForObject(THROW_DICE_URL, ScoreVo.class);
			score = scoreVo.getScore();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return score;
	}
	
	
}
