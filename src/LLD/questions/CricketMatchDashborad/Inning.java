package LLD.questions.CricketMatchDashborad;

public class Inning {
    private Team battingTeam;
    private int totalOvers;
    private int ballsBowled;
    private int currentOverBalls;
    private Player striker;
    private Player nonStriker;
    private boolean isCompleted;

    public Inning(Team battingTeam, int totalOvers) {
        this.battingTeam = battingTeam;
        this.totalOvers = totalOvers;
        this.ballsBowled = 0;
        this.currentOverBalls = 0;
        this.isCompleted = false;

        // Start with first __2__ batsmen
        striker = battingTeam.getNextBatsman();
        striker.changeStrike();
        striker.setActive(true);
        nonStriker = battingTeam.getNextBatsman();
        nonStriker.setActive(true);
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void playBall(String ballInput) {
        if (isCompleted) {
            return;
        }

        switch (ballInput) {
            case "Wd":
                battingTeam.addExtras(1);
                // Wide ball, no ball counted
                return;
            case "Nb":
                battingTeam.addExtras(1);
                // No ball, again extra ball
                return;
            case "W":
                striker.getOut();
                battingTeam.loseWicket();
                ballsBowled++;
                currentOverBalls++;

                // Check if players left
                Player nextBatsman = battingTeam.getNextBatsman();
                if (nextBatsman != null) {
                    striker = nextBatsman;
                    striker.changeStrike();
                    striker.setActive(true);
                } else {
                    isCompleted = true;
                }
                break;
            default:
                // It's a run (0,1,2,3,4,6)
                int runs = Integer.parseInt(ballInput);
                striker.scoreRuns(runs);
                battingTeam.addRuns(runs);

                ballsBowled++;
                currentOverBalls++;

                if (runs % 2 != 0) {
                    swapStrike();
                }
                break;
        }

        if (currentOverBalls == 6) {
            endOver();
        }

        if (ballsBowled / 6 >= totalOvers) {
            isCompleted = true;
        }
    }

    private void swapStrike() {
        Player temp = striker;
        striker = nonStriker;
        nonStriker = temp;

        striker.changeStrike();
        nonStriker.changeStrike();
    }

    private void endOver() {
        currentOverBalls = 0;
        swapStrike();
        System.out.println();
        printScorecard();
    }

    public void printScorecard() {
        System.out.println("Scorecard for " + battingTeam.getName() + ":");
        System.out.println("Player Name  Score  4s  6s  Balls");

        for (Player player : battingTeam.getPlayers()) {
            System.out.println(player.toString());
        }

        int overs = ballsBowled / 6;
        int balls = ballsBowled % 6;

        System.out.println("Total: " + battingTeam.getTotalRuns() + "/" + battingTeam.getTotalWickets());
        System.out.println("Overs: " + overs + "." + balls);
        System.out.println();
    }
}
