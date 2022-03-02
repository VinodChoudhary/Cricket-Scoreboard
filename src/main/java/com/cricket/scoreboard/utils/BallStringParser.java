package main.java.com.cricket.scoreboard.utils;

import main.java.com.cricket.scoreboard.exceptions.InvalidOverBallException;
import main.java.com.cricket.scoreboard.model.over.ball.Ball;
import main.java.com.cricket.scoreboard.model.over.ball.BallType;
import main.java.com.cricket.scoreboard.model.over.ball.Run;
import main.java.com.cricket.scoreboard.model.over.ball.RunType;
import main.java.com.cricket.scoreboard.model.team.Player;

public class BallStringParser {

    public static Ball getBall(Player batter, Player bowler, String ballString) throws InvalidOverBallException {
        if(ballString.equals("W"))
        {
            return new Ball(batter, bowler, BallType.WICKET, new Run(0, RunType.NORMAL));
        }
        else if(ballString.equals("Wd"))
        {
            return new Ball(batter, bowler, BallType.WIDE, new Run(1, RunType.EXTRA));
        }
        else if(ballString.equals("NB"))
        {
            return new Ball(batter, bowler, BallType.NO_BALL, new Run(1, RunType.EXTRA));
        }
        else
        {
            int runs;
            try
            {
                runs = Integer.parseInt(ballString);
            }
            catch (Throwable e)
            {
                throw new InvalidOverBallException(String.format("'%s' is not an accepted value for ball in an over", ballString));
            }
            RunType runType = (runs == 4) ? RunType.FOUR : (runs == 6 ? RunType.SIX : RunType.NORMAL);
            return new Ball(batter, bowler, BallType.NORMAL, new Run(runs, runType));
        }
    }
}
