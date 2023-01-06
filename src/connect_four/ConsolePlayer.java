// connect_four.ConsolePlayer.java
// Dr. Schwesinger's code for an extension of the Player class that gets text
// input from the console

package connect_four;

import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import connect_four.Board;
import connect_four.Board.Disc;
import connect_four.Board.Position;

/**
 * ConsolePlayer uses the console to get moves from a Player
 */
public class ConsolePlayer extends Player {
    private Scanner input;

    /**
     * The constructor for ConsolePlayer requires a Scanner object
     * @param disc the player's disc
     * @param input a Scanner object created by the caller
     */
    public ConsolePlayer(Disc disc, Scanner input) {
        super(disc);
        this.input = input;
    }

    /**
     * getMove gets a player's move via the text console
     * @param board the Board object representing the current state of the game
     * @return a PlayerMove object indicating the player's move
     */
    public PlayerMove getMove(Board board) {
        String playerName = player_disc == Disc.YELLOW ? "Yellow" : "Red";

        while(true) {
            System.out.printf("Enter the move for %s or \"quit\", \"reset\"\n", playerName);
            String move = input.nextLine();
            try {
                // split the input into whitespace separated tokens
                String[] splitArray = move.split("\\s+");

                if (splitArray.length == 1) {
                    if (splitArray[0].toLowerCase().equals("quit")) {
                        return new PlayerMove(Action.QUIT);
                    }
                    if (splitArray[0].toLowerCase().equals("reset")) {
                        return new PlayerMove(Action.RESET);
                    }
                    if (Pattern.matches("[1-9][0-9]*", splitArray[0])) {
                        return new PlayerMove(
                                Action.PLAY,
                                new Position(Integer.parseInt(splitArray[0]))
                                );
                    }
                    System.out.println("Invalid move. Try again.");
                }
                else {
                    System.out.println("Invalid move. Try again.");
                }
            }
            catch (PatternSyntaxException ex) {
                // do nothing
            } catch (GameException e) {
                System.out.println(e);
            }
        }
    }

}
