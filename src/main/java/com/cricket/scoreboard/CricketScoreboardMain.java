package main.java.com.cricket.scoreboard;

import main.java.com.cricket.scoreboard.exceptions.InvalidMatchInningsException;
import main.java.com.cricket.scoreboard.exceptions.InvalidOverBallException;
import main.java.com.cricket.scoreboard.exceptions.InvalidPlayingTeamException;
import main.java.com.cricket.scoreboard.model.match.Innings;
import main.java.com.cricket.scoreboard.model.match.LimitedOverMatch;
import main.java.com.cricket.scoreboard.model.over.Over;
import main.java.com.cricket.scoreboard.model.over.ball.Ball;
import main.java.com.cricket.scoreboard.model.team.Player;
import main.java.com.cricket.scoreboard.model.team.PlayingTeam;
import main.java.com.cricket.scoreboard.model.team.Team;
import main.java.com.cricket.scoreboard.utils.BallStringParser;
import main.java.com.cricket.scoreboard.utils.ScoreboardPrinter;

import java.util.Scanner;

public class CricketScoreboardMain {
    public static void main(String[] args) throws InvalidOverBallException, InvalidPlayingTeamException, InvalidMatchInningsException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("No. of players for each team:");
        int teamSize = scanner.nextInt();
        if (teamSize < 2) {
            throw new IllegalArgumentException("Team size cannot be less than 2.");
        }

        System.out.println("No. of overs:");
        int numberOfOvers = scanner.nextInt();

        Team team1 = new Team("1");
        Team team2 = new Team("2");

        LimitedOverMatch limitedOverMatch = new LimitedOverMatch(team1, team2, numberOfOvers);

        Innings firstInnings = startInnings(scanner, team1, teamSize, numberOfOvers, null);
        limitedOverMatch.addInnings(team1, firstInnings);

        Innings secondInnings = startInnings(scanner, team2, teamSize, numberOfOvers, firstInnings.getTotalScore() + 1);
        limitedOverMatch.addInnings(team2, secondInnings);

        System.out.println(limitedOverMatch.whoWon());

        scanner.close();
    }

    private static Innings startInnings(Scanner scanner, Team team, int teamSize, int numberOfOvers, Integer targetScore) throws InvalidPlayingTeamException, InvalidOverBallException {
        addTeamPlayers(scanner, team, teamSize);

        PlayingTeam playingTeam = new PlayingTeam(team, team.getPlayers());
        Innings innings = new Innings(playingTeam, targetScore);

        Player unknownBowler = new Player("UNKNOWN"); // This is created because the way input is taken bowler is not known in advance, hence a dummy bowler is being used for now
        for (int i = 0; i < numberOfOvers && !innings.isInningOver(); ++i) {

            System.out.println("Over " + (i + 1) + ":");
            Over over = new Over(i + 1);
            innings.overStarted(over);

            while (over.getValidBallsBowled() < Over.MAX_VALID_BALLS_IN_OVER) {
                String ballString = scanner.next();
                Ball ball = BallStringParser.getBall(innings.getStrikerBatter(), unknownBowler, ballString);
                innings.ballBowled(ball);
                if (innings.isInningOver()) {
                    break;
                }
            }

            innings.overFinished();
            ScoreboardPrinter.printScoreboard(innings);
        }

        return innings;
    }

    private static void addTeamPlayers(Scanner scanner, Team team, int teamSize) {
        System.out.println("Batting Order for team " + team.getName() + ":");
        for (int i = 0; i < teamSize; ++i) {
            String playerName = scanner.next();
            team.addPlayer(new Player(playerName));
        }
    }
}
