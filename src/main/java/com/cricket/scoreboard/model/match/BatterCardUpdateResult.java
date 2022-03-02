package main.java.com.cricket.scoreboard.model.match;

public class BatterCardUpdateResult {
    private boolean wicket;
    private boolean strikeChanged;

    public BatterCardUpdateResult(boolean wicket, boolean strikeChanged) {
        this.wicket = wicket;
        this.strikeChanged = strikeChanged;
    }

    public boolean isWicket() {
        return wicket;
    }

    public boolean isStrikeChanged() {
        return strikeChanged;
    }
}
