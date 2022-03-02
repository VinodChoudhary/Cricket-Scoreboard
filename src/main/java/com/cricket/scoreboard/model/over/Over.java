package main.java.com.cricket.scoreboard.model.over;

import main.java.com.cricket.scoreboard.model.over.ball.Ball;

import java.util.ArrayList;
import java.util.List;

public class Over {
    public static int MAX_VALID_BALLS_IN_OVER = 6;

    private int overNumber;
    private int validBallsBowled = 0;
    private List<Ball> balls = new ArrayList<>();

    public Over(int overNumber) {
        this.overNumber = overNumber;
    }

    public int getValidBallsBowled() {
        return validBallsBowled;
    }

    public void addBall(Ball ball) {
        this.balls.add(ball);

        if (!ball.getBallType().isExtraBall()) {
            validBallsBowled++;
        }
    }
}
