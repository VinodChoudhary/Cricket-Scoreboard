package main.java.com.cricket.scoreboard.model.match;

import main.java.com.cricket.scoreboard.model.over.ball.Ball;
import main.java.com.cricket.scoreboard.model.over.ball.BallType;
import main.java.com.cricket.scoreboard.model.over.ball.RunType;
import main.java.com.cricket.scoreboard.model.team.Player;

public class BatterCard {
    private Player batter;
    private boolean isOut = false;
    private int runsScored = 0;
    private int ballsFaced = 0;
    private int fours = 0;
    private int sixes = 0;

    public BatterCard(Player batter) {
        this.batter = batter;
    }

    public Player getBatter() {
        return batter;
    }

    public boolean isOut() {
        return isOut;
    }

    public int getRunsScored() {
        return runsScored;
    }

    public int getBallsFaced() {
        return ballsFaced;
    }

    public int getFours() {
        return fours;
    }

    public int getSixes() {
        return sixes;
    }

    public BatterCardUpdateResult updateCard(Ball ball) {
        boolean wicket = false;
        boolean strikeChanged = false;

        if (ball.getBallType().equals(BallType.WICKET)) {
            this.isOut = true;
            wicket = true;
        }

        if (!ball.getRun().getRunType().equals(RunType.EXTRA)) {
            this.runsScored += ball.getRun().getRuns();

            if (ball.getRun().getRunType().equals(RunType.NORMAL) && ball.getRun().getRuns() % 2 == 1) {
                strikeChanged = true;
            }
        }

        if (!ball.getBallType().equals(BallType.WIDE)) this.ballsFaced++;

        if (ball.getRun().getRunType().equals(RunType.FOUR)) this.fours++;

        if (ball.getRun().getRunType().equals(RunType.SIX)) this.sixes++;

        return new BatterCardUpdateResult(wicket, strikeChanged);
    }
}
