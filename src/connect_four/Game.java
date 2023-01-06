// connect_four.Game.java
// CSC 243, Spring 2020
// Author: Dr. Schwesinger

package connect_four;

import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import connect_four.Board;
import connect_four.Board.Disc;
import connect_four.Board.Position;
import connect_four.Player.PlayerMove;

/**
 *  The Game class has the game logic for a text based game of Connect Four
 */
public class Game {
    private Board board;
    private Scanner input;
    private Map<Disc, Player> players;
    private Disc currentPlayer;

    public Game() {
        this(false, false);
    }

    public Game(boolean use_AI_for_yellow, boolean use_AI_for_red) {
        board = new Board();
        input = new Scanner(System.in);
        players = new HashMap<>();

        if (use_AI_for_yellow) {
            players.put(Disc.YELLOW, new AIPlayer(Disc.YELLOW));
        }
        else {
            players.put(Disc.YELLOW, new ConsolePlayer(Disc.YELLOW, input));
        }

        if (use_AI_for_red) {
            players.put(Disc.RED, new AIPlayer(Disc.RED));
        }
        else {
            players.put(Disc.RED, new ConsolePlayer(Disc.RED, input));
        }
        currentPlayer = Disc.YELLOW; // yellow goes first
    }

    /**
     * The run loop for the text based game of Connect Four
     */
    public void run() {
        System.out.println("Connect Four\n");

        // the game loop
        while (true) {

            // print the board to the screen
            System.out.println(board.toString());

            System.out.print("Available moves: ");
            List<Integer> validMoves = board.getValidMoves();
            for (int i = 0; i < validMoves.size(); i++) {
                System.out.print(validMoves.get(i) + " ");
            }
            System.out.println("");

            // get the play move and perform the appropriate action
            PlayerMove pm = players.get(currentPlayer).getMove(board);
            switch (pm.action) {
                case QUIT:
                    System.exit(0);
                    break;
                case PLAY:
                    try {
                        board.playMove(currentPlayer, pm.position);
                    } catch (GameException e) {
                        System.out.println(e);
                        break;
                    }

                    Disc winner = null;
                    try {
                        winner = board.checkWinner();
                    } catch (DrawException e) {
                        System.out.println("Draw");
                        System.exit(0);
                    }
                    if (winner != Disc.EMPTY) {
                        System.out.println("Game over");
                        String playerName = (currentPlayer == Disc.YELLOW) ? "Yellow" : "Red";
                        System.out.println(playerName + " player wins");
                        System.out.println(board);
                        System.exit(0);
                    }

                    // switch player
                    currentPlayer = (currentPlayer == Disc.YELLOW) ?
                        Disc.RED : Disc.YELLOW;
                    break;
                case RESET:
                    System.out.println("Game Reset");
                    board = new Board();
                    break;
                default:
                    System.out.println("Unknown action");
            }

        }
    }


    /**
     * Play a text based game of Connect Four to completion
     *
     * @param args the command line argument array
     */
    public static void main(String[] args) {
        // Parse command line arguments
        boolean use_AI_for_yellow = false;
        boolean use_AI_for_red = false;
        for (String s: args) {
            if (s.equals("-y")) {
                use_AI_for_yellow = true;
            }
            if (s.equals("-r")) {
                use_AI_for_red = true;
            }
        }

        Game g = new Game(use_AI_for_yellow, use_AI_for_red);
        g.run();
    }
}
