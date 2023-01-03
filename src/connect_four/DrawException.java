// file: connect_four.DrawException.java

package connect_four;

// TODO create an exception named DrawException that is a subtype of
// GameException

public class DrawException extends GameException {
    public DrawException(String message) {
        super(message);
    }

}


