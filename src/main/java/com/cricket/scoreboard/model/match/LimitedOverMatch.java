package main.java.com.cricket.scoreboard.model.match;

import main.java.com.cricket.scoreboard.exceptions.InvalidMatchInningsException;
import main.java.com.cricket.scoreboard.model.team.Team;

import java.util.List;

public class LimitedOverMatch extends Match {

    private int numberOfOvers;

    public LimitedOverMatch(Team team1, Team team2, int numberOfOvers) {
        super(team1, team2);
        this.numberOfOvers = numberOfOvers;
    }

    @Override
    public String whoWon() throws InvalidMatchInningsException {

        List<Innings> team1Innings = this.teamToInningsMap.get(this.team1);
        if (team1Innings.size() != 1) {
            throw new InvalidMatchInningsException(String.format("In match type '%s', innings are expected to be exactly 1 for team '%s'", LimitedOverMatch.class.getName(), team1.getName()));
        }
        List<Innings> team2Innings = this.teamToInningsMap.get(this.team2);
        if (team2Innings.size() != 1) {
            throw new InvalidMatchInningsException(String.format("In match type '%s', innings are expected to be exactly 1 for team '%s'", LimitedOverMatch.class.getName(), team2.getName()));
        }
        if (team1Innings.get(0).getTotalScore() > team2Innings.get(0).getTotalScore()) {
            return String.format("Team %s won the match by %d runs", team1.getName(), team1Innings.get(0).getTotalScore() - team2Innings.get(0).getTotalScore());
        }
        if (team1Innings.get(0).getTotalScore() < team2Innings.get(0).getTotalScore()) {
            return String.format("Team %s won the match by %d wickets", team2.getName(), team2.getPlayers().size() - team2Innings.get(0).getWickets() - 1);
        }
        return String.format("Match is tied between team '%s' & team '%s'.", this.team1.getName(), this.team2.getName());
    }
}
