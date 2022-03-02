package main.java.com.cricket.scoreboard.utils;

import main.java.com.cricket.scoreboard.model.match.BatterCard;
import main.java.com.cricket.scoreboard.model.match.Innings;
import main.java.com.cricket.scoreboard.model.over.Over;
import main.java.com.cricket.scoreboard.model.team.Player;

import java.util.List;

public class ScoreboardPrinter {
    public static void printScoreboard(Innings innings) {
        System.out.printf("%n");
        System.out.printf("Scorecard for Team %s:%n", innings.getBattingSide().getTeam().getName());
        System.out.printf("%s %10s %10s %10s %10s%n", "Player Name", "Score", "4s", "6s", "Balls");
        for (int i = 0; i < innings.getBattingSide().getBattingOrder().size(); ++i) {
            Player batter = innings.getBattingSide().getBattingOrder().get(i);
            String isCurrentlyPlaying = batter.getName().equals(innings.getStrikerBatter().getName()) || batter.getName().equals(innings.getNonStrikerBatter().getName()) ? "*" : "";
            if (innings.getBatterCardMap().containsKey(batter)) {
                BatterCard batterCard = innings.getBatterCardMap().get(batter);
                System.out.printf("%s%s %10d %10d %10d %10d%n", batter.getName(), isCurrentlyPlaying, batterCard.getRunsScored(), batterCard.getFours(), batterCard.getSixes(), batterCard.getBallsFaced());
            } else {
                System.out.printf("%s %10d %10d %10d %10d%n", batter.getName(), 0, 0, 0, 0);
            }
        }
        System.out.printf("Total: %d/%d%n", innings.getTotalScore(), innings.getWickets());
        System.out.printf("Overs: %s%n", getOversBowled(innings.getOvers()));
        System.out.printf("%n");
    }

    private static String getOversBowled(List<Over> overs) {
        int oversSize = overs.size();
        if (oversSize == 0)
            return "0";
        int lastOverValidBallBowled = overs.get(oversSize - 1).getValidBallsBowled();
        if (lastOverValidBallBowled == Over.MAX_VALID_BALLS_IN_OVER)
            return "" + oversSize;
        return oversSize - 1 + "." + lastOverValidBallBowled;
    }
}
