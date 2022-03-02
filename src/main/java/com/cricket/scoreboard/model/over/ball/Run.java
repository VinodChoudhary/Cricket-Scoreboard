package main.java.com.cricket.scoreboard.model.over.ball;

public class Run {
    private int runs;
    private RunType runType;

    public Run(int runs, RunType runType) {
        this.runs = runs;
        this.runType = runType;
    }

    public int getRuns() {
        return runs;
    }

    public RunType getRunType() {
        return runType;
    }
}
