// connect_four.Player.java
// CSC 243, Spring 2020
// Dr. Schwesinger's definition for the Player abstract class

package connect_four;

import connect_four.Board.Disc;
import connect_four.Board.Position;

public abstract class Player {

    public enum Action {
        PLAY,
        QUIT,
        RESET,
        UNDO
    }

    public static class PlayerMove {
        Action action;
        Position position;
        PlayerMove(Action action) {
            this.action = action;
        }
        PlayerMove(Action action, Position position) {
            this.action = action;
            this.position = position;
        }
    }

    Disc player_disc;

    public Player(Disc player_disc) {
        this.player_disc = player_disc;
    }

    public abstract PlayerMove getMove(Board board);
}
