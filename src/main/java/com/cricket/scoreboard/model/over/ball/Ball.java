package main.java.com.cricket.scoreboard.model.over.ball;

import main.java.com.cricket.scoreboard.model.team.Player;

public class Ball {
    private Player batter;
    private Player bowler;
    private BallType ballType;
    private Run run;

    public Ball(Player batter, Player bowler, BallType ballType, Run run) {
        this.batter = batter;
        this.bowler = bowler;
        this.ballType = ballType;
        this.run = run;
    }

    public BallType getBallType() {
        return this.ballType;
    }

    public Run getRun() {
        return this.run;
    }
}
