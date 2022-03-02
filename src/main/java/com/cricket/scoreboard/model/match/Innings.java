package main.java.com.cricket.scoreboard.model.match;

import main.java.com.cricket.scoreboard.exceptions.InvalidPlayingTeamException;
import main.java.com.cricket.scoreboard.model.over.Over;
import main.java.com.cricket.scoreboard.model.over.ball.Ball;
import main.java.com.cricket.scoreboard.model.team.Player;
import main.java.com.cricket.scoreboard.model.team.PlayingTeam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Innings {
    private PlayingTeam battingSide;
    private Player strikerBatter;
    private Player nonStrikerBatter;
    private Map<Player, BatterCard> batterCardMap = new HashMap<>();
    private List<Over> overs = new ArrayList<>();
    private int wickets = 0;
    private int totalScore = 0;
    private Integer targetScore;
    private int totalTeamExtras = 0;

    /* To be used for keeping track of bowling stats
    private PlayingTeam bowlingSide;
    private Player bowler;
    private Map<Player, BowlerCard> bowlerCardMap;
     */

    public Innings(PlayingTeam battingSide, Integer targetScore) throws InvalidPlayingTeamException {
        this.battingSide = battingSide;
        if (battingSide.getBattingOrder().size() < 2) {
            throw new InvalidPlayingTeamException(String.format("Found only '%d' players in team '%s'", battingSide.getBattingOrder().size(), battingSide.getTeam().getName()));
        }
        this.strikerBatter = battingSide.getBattingOrder().get(0);
        this.nonStrikerBatter = battingSide.getBattingOrder().get(1);

        this.batterCardMap.put(strikerBatter, new BatterCard(strikerBatter));
        this.batterCardMap.put(nonStrikerBatter, new BatterCard(nonStrikerBatter));

        this.targetScore = targetScore;
    }

    public int getWickets() {
        return wickets;
    }

    public int getTotalTeamExtras() {
        return totalTeamExtras;
    }

    public PlayingTeam getBattingSide() {
        return battingSide;
    }

    public List<Over> getOvers() {
        return overs;
    }

    public Player getNonStrikerBatter() {
        return nonStrikerBatter;
    }

    public Map<Player, BatterCard> getBatterCardMap() {
        return batterCardMap;
    }

    public int getTotalScore() {
        return totalScore;
    }

    public void overStarted(Over over) {
        this.overs.add(over);
    }

    public void ballBowled(Ball ball) {
        this.overs.get(this.overs.size() - 1).addBall(ball);

        BatterCard strikerBatterCard = batterCardMap.get(strikerBatter);
        BatterCardUpdateResult updateResult = strikerBatterCard.updateCard(ball);

        if (updateResult.isWicket()) {
            this.wickets++;
            if (isInningOver()) return;

            strikerBatter = battingSide.getBattingOrder().get(this.wickets + 1);
            this.batterCardMap.put(strikerBatter, new BatterCard(strikerBatter));
        }
        if (updateResult.isStrikeChanged()) {
            Player temp = strikerBatter;
            strikerBatter = nonStrikerBatter;
            nonStrikerBatter = temp;
        }

        this.totalScore += ball.getRun().getRuns();

        if (ball.getBallType().isExtraBall()) {
            this.totalTeamExtras += ball.getRun().getRuns();
        }
    }

    public boolean isInningOver() {
        return (this.wickets >= battingSide.getBattingOrder().size() - 1) || (this.targetScore != null && this.targetScore <= this.totalScore);
    }

    public void overFinished() {
        Player temp = strikerBatter;
        strikerBatter = nonStrikerBatter;
        nonStrikerBatter = temp;
    }

    public Player getStrikerBatter() {
        return this.strikerBatter;
    }
}
