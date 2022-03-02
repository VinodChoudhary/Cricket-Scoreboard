package main.java.com.cricket.scoreboard.model.match;

import main.java.com.cricket.scoreboard.model.over.ball.Ball;
import main.java.com.cricket.scoreboard.model.team.Player;

import java.util.HashMap;
import java.util.Map;

/*
To be used in future to keep track of bowler's stats
 */
public class BowlerCard {
    private Player bowler;
    private Map<Integer, Integer> overToBallsMap = new HashMap<>();
    private int runsConceded = 0;
    private int wicketsTaken = 0;
    private int maidenOver = 0;
    private int dotBalls = 0;

    public BowlerCard(Player batter) {
        this.bowler = batter;
    }

    public void updateCard(Ball ball) {

    }

    public int oversBowled() {
        return 0;
    }
}
