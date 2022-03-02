package main.java.com.cricket.scoreboard.model.team;

import java.util.List;

public class PlayingTeam {
    private Team team;
    private List<Player> battingOrder;
    /* To be used to keep track of bowling order in future
    private List<Player> bowlingOrder;
     */

    public PlayingTeam(Team team, List<Player> battingOrder) {
        this.team = team;
        this.battingOrder = battingOrder;
    }

    public Team getTeam() {
        return team;
    }

    public List<Player> getBattingOrder() {
        return battingOrder;
    }
}
