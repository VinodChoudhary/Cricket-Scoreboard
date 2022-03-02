package main.java.com.cricket.scoreboard.model.match;

import main.java.com.cricket.scoreboard.exceptions.InvalidMatchInningsException;
import main.java.com.cricket.scoreboard.model.team.Team;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class Match {
    protected Team team1;
    protected Team team2;
    protected Map<Team, List<Innings>> teamToInningsMap = new HashMap<>();

    protected Match(Team team1, Team team2) {
        this.team1 = team1;
        this.team2 = team2;
    }

    abstract public String whoWon() throws InvalidMatchInningsException;

    public void addInnings(Team team, Innings innings) {
        this.teamToInningsMap.putIfAbsent(team, new ArrayList<>());
        this.teamToInningsMap.get(team).add(innings);
    }
}
