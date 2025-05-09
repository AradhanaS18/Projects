import java.util.Random;
import java.util.Scanner;


class Player {
    private String name;
    private int runs;
    private int wickets;
    private int ballsRemaining;

    public Player(String name, int totalBalls, int totalWickets) {
        this.name = name;
        this.runs = 0;
        this.wickets = totalWickets;
        this.ballsRemaining = totalBalls;
    }

    public String getName() { return name; }
    public int getRuns() { return runs; }
    public int getWicketsLost() { return 3 - wickets; }
    public int getWickets() { return wickets; }
    public int getBallsRemaining() { return ballsRemaining; }

    public boolean isOutOfBallsOrWickets() {
        return ballsRemaining <= 0 || wickets <= 0;
    }

    public void playBall(int run) {
        ballsRemaining--;
        if (run == 7) {
            System.out.println("Ooops, OUT!");
            wickets--;
        } else {
            if (run == 4) {
                System.out.println(run + " runs\nStraight 4 RUNS, HURRAY BOUNDARY!!!");
            } else if (run == 6) {
                System.out.println(run + " runs\nIt's a SIXER, HURRAY!!!");
            } else {
                System.out.println(run + " runs");
            }
            runs += run;
        }
    }

    public void showScore() {
        int ballsUsed = (overs * 6) - ballsRemaining;
        int oversPlayed = ballsUsed / 6;
        int balls = ballsUsed % 6;
        System.out.println("\n" + name + " SCORE:");
        System.out.println("Runs: " + runs);
        System.out.println("Wickets: " + getWicketsLost());
        System.out.println("Overs: " + oversPlayed + "." + balls + "\n");
    }

    public static int overs; // Shared by all players
}

class Umpire {
    public void announceToss(String battingTeam) {
        System.out.println("\n--- TOSS RESULT ---");
        System.out.println(battingTeam + " won the toss and will bat first.\n");
    }

    public void announceInnings(String teamName) {
        System.out.println("========== " + teamName + " is Batting ==========\n");
    }

    public void announceResult(Player p1, Player p2) {
        int p1Runs = p1.getRuns();
        int p2Runs = p2.getRuns();

        System.out.println("========== MATCH RESULT ==========");

        if (p1Runs > p2Runs) {
            System.out.println(p1.getName() + " wins by " + (p1Runs - p2Runs) + " runs!");
        } else if (p2Runs > p1Runs) {
            System.out.println(p2.getName() + " wins by " + (3 - p2.getWicketsLost()) + " wickets!");
        } else {
            System.out.println("Match Drawn!");
        }
    }
}

class Match {
    private Player player1;
    private Player player2;
    private int overs;
    private final int[] runChoices = {0, 1, 2, 3, 4, 6, 7}; // 7 = OUT
    private Random rand;
    private Scanner input;
    private Umpire umpire;

    public Match(int overs) {
        this.overs = overs;
        Player.overs = overs;
        int totalBalls = overs * 6;
        player1 = new Player("TEAM A", totalBalls, 3);
        player2 = new Player("TEAM B", totalBalls, 3);
        rand = new Random();
        input = new Scanner(System.in);
        umpire = new Umpire();
    }

    public void playMatch() {
        umpire.announceToss(player1.getName());

        umpire.announceInnings(player1.getName());
        playInnings(player1);
        System.out.println("TEAM A's FINAL SCORE:");
        player1.showScore();

        umpire.announceInnings(player2.getName());
        playInnings(player2, player1.getRuns());
        System.out.println("TEAM B's FINAL SCORE:");
        player2.showScore();

        umpire.announceResult(player1, player2);
    }

    private void playInnings(Player player) {
        while (!player.isOutOfBallsOrWickets()) {
            showInningsOptions(player);
            int choice = input.nextInt();
            handleInput(player, choice);
        }
    }

    private void playInnings(Player player, int target) {
        while (!player.isOutOfBallsOrWickets() && player.getRuns() <= target) {
            showInningsOptions(player, target);
            int choice = input.nextInt();
            handleInput(player, choice, target);
        }
    }

    private void showInningsOptions(Player player) {
        System.out.println("----------------------------------------------------------------");
        System.out.println(player.getName() + " Batting");
        System.out.println("5 - HIT THE BALL");
        System.out.println("9 - CHECK SCORE");
        System.out.println("7 - SHOW TARGET(only 2nd Innings)");
        System.out.print("Enter choice: ");
    }

    private void showInningsOptions(Player player, int target) {
        showInningsOptions(player);
        
       
    }

    private void handleInput(Player player, int choice) {
        if (choice == 5) {
            int run = runChoices[rand.nextInt(runChoices.length)];
            player.playBall(run);
        } else if (choice == 9) {
            player.showScore();
        } else {
            System.out.println("Invalid choice!");
        }
    }

    private void handleInput(Player player, int choice, int target) {
        if (choice == 7) {
            System.out.println("Target: " + (target + 1));
            System.out.println("Current: " + player.getRuns() + "-" + player.getWicketsLost());
            System.out.println("Balls left: " + player.getBallsRemaining() + "\n");
        } else {
            handleInput(player, choice);
        }
    }

    public void showRules() {
        System.out.println("\n--- GAME RULES ---");
        System.out.println("TEAM A bats first.");
        System.out.println("Match consists of " + overs + " overs.");
        System.out.println("Each team has 3 wickets.");
        System.out.println("Press 5 to play shot, 9 for score, 7 for target (Player 2 only).\n");
    }
}

public class mini_cricket {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int userInput;

        do {
            System.out.println("\n================== MINI CRICKET GAME ==================");
            System.out.println("1 - PLAY");
            System.out.println("2 - INSTRUCTIONS");
            System.out.println("0 - EXIT");
            System.out.print("Enter your choice: ");
            userInput = input.nextInt();

            switch (userInput) {
                case 1:
                    System.out.print("Enter number of overs: ");
                    int overs = input.nextInt();
                    Match match = new Match(overs);
                    match.playMatch();
                    break;
                case 2:
                    System.out.println("Instructions will appear during the match.");
                    break;
                case 0:
                    System.out.println("Thanks for playing!");
                    break;
                default:
                    System.out.println("Invalid option, try again.");
            }
        } while (userInput != 0);
    }
}