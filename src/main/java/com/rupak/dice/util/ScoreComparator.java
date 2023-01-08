package com.rupak.dice.util;

import java.util.Comparator;
import com.rupak.dice.model.CurrentScoreVo;

public class ScoreComparator implements Comparator<CurrentScoreVo>{

	@Override
	public int compare(CurrentScoreVo s1, CurrentScoreVo s2) {
		if(s2.getCurrentScore() == s1.getCurrentScore()) {
			return 0;
		}
		else if(s2.getCurrentScore() > s1.getCurrentScore()) {
			return 1;
		}
		else {
			return -1;
		}
	}
}
