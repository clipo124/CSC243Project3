// file: connect_four.AIPlayer.java

package connect_four;

import connect_four.Board;
import connect_four.Board.Disc;
import connect_four.Board.Position;
import java.util.Random;

// TODO complete the implementation of the AIPlayer class
public class AIPlayer extends Player {

    AIPlayer(Disc player_disc) {
        super(player_disc);
    }

    @Override
    public PlayerMove getMove(Board board) {
        PlayerMove result = null;
        try {
            int validMoves[] = board.getValidMoves();
            Random move = new Random();
            int maxRange = validMoves.length;
            int nextMove = move.nextInt(1, maxRange);

            Position position = new Position(validMoves[nextMove]);
            result = new PlayerMove(Action.PLAY, position);
            return result;
        } catch (GameException e) {
            System.out.println("Something went wrong. AI Player can not make a move.");
        }
        return result;
    }
}


