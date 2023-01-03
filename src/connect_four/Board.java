// connect_four.Board.java
// CSC 243, Spring 2020
// Dr. Schwesinger's implementation of the Board class

package connect_four;

/**
 * The Board class is a representation of the board state for a game of Connect
 * Four
 */
public class Board {

    public enum Disc {
        EMPTY,
        YELLOW,
        RED
    }

    public static class Position {
        public int column;
        // TODO have the constructor throw a GameException if the input
        // column is not a valid move
        Position(int column) throws GameException {
            if (column < 1 || column > COLUMNS) {
                throw new GameException("That move was not within range.");
            } else {
                this.column = column;
            }
        }
    }

    private static final int COLUMNS = 7;
    private static final int ROWS = 6;
    private static final int CONNECT = 4;

    private Disc [][] board;

    /**
     * The Board constructor initializes the board state to the starting
     * configuration of the game
     */
    public Board() {
        board = new Disc[ROWS][COLUMNS];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = Disc.EMPTY;
            }
        }
    }

    /**
     * playMove attempts to play the move indicated by the player and house
     * parameters. If the move is invalid, then a result of false is returned.
     *
     * @param disc a representation of the player.
     * @param position an representation of the column.
     * @return a boolean indicating whether the move was successful
     */
    public void playMove(Disc disc, Position position) throws GameException {
        // TODO update this method according to the new formal parameter types.

        for (int row = 0; row < ROWS; row++) {
            if (board[0][position.column-1] != disc.EMPTY) {
                throw new GameException("This column is full.");
            }

            if (board[row][position.column-1] == disc.EMPTY) {
                // do nothing
            } else if (board[row][position.column-1] != disc.EMPTY) {
                board[row-1][position.column-1] = disc;
                break;
            }
            if (row == ROWS-1 && board[row][position.column-1] == disc.EMPTY) {
                board[row][position.column-1] = disc;
            }
        }

    }

    /**
     * getValidMoves returns an array of valid moves
     * @return an array of valid moves
     */
    public int[] getValidMoves() {
        int [] result = null;

        int valid_count = 0;
        for (int col = 0; col < COLUMNS; col++) {
            if (board[0][col] == Disc.EMPTY) {
                valid_count += 1;
            }
        }

        result = new int[valid_count];
        int index = 0;
        for (int col = 0; col < COLUMNS; col++) {
            if (board[0][col] == Disc.EMPTY) {
                result[index] = col+1;
                index += 1;
            }
        }

        return result;
    }

    /**
     * checkWinner determines if there is a current winner and returns the Disc
     * representation of the winner. If there is no winner, then Disc.EMPTY is
     * returned.
     *
     * @return a Disc of the winnining player
     */
    // TODO change checkWinner to throw a DrawException if there are no
    // valid moves left
    public Disc checkWinner() throws DrawException {
        Disc result = Disc.EMPTY;

        int [] validMoves = getValidMoves();
        if (validMoves.length == 0) {
            // TODO ???
            throw new DrawException("No more moves.");
        }

        // TODO update winner checking code according to the new types.
        // Check Player Y
        // Check horizontal
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length-3; j++) {
                if (board[i][j] == Disc.YELLOW && board[i][j+1] == Disc.YELLOW && board[i][j+2] == Disc.YELLOW && board[i][j+3] == Disc.YELLOW) {
                    result = Disc.YELLOW;
                }
            }
        }
        // check vertical
        for (int i = 0; i < board.length-3; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == Disc.YELLOW && board[i+1][j] == Disc.YELLOW && board[i+2][j] == Disc.YELLOW && board[i+3][j] == Disc.YELLOW) {
                    result = Disc.YELLOW;
                }
            }
        }
        // check diagonal starting at row
        for (int i = 0; i < board.length; i++) {
            int row = i;
            int column = 0;
            int countChip = 0;
            while (row > 0) {
                if (board[row][column] == Disc.YELLOW) {
                    countChip++;
                } else {
                    countChip = 0;
                }
                row--;
                column++;
            }

            if (row == 0) {
                if (board[row][column] == Disc.YELLOW) {
                    countChip++;
                }
            }

            if (countChip == 4) {
                result = Disc.YELLOW;
                return result;
            }
        }
        // check diagonal starting at column
        for (int i = 0; i < board[board.length-1].length; i++) {
            int row = board.length-1;
            int column = i;
            int countChip = 0;
            while (row > 0 && column < board[board.length-1].length) {
                if (board[row][column] == Disc.YELLOW) {
                    countChip++;
                } else {
                    countChip = 0;
                }
                row--;
                column++;
            }

            if (column == 0) {
                if (board[row][column] == Disc.YELLOW) {
                    countChip++;
                }
            }

            if (countChip == 4) {
                result = Disc.YELLOW;
                return result;
            }
        }

        // check reverse diagonal starting at last row
        for (int i = 0; i < board.length; i++) {
            int row = i;
            int column = board[0].length-1;
            int countChip = 0;
            while (row > 0) {
                if (board[row][column] == Disc.YELLOW) {
                    countChip++;
                } else {
                    countChip = 0;
                }
                row--;
                column--;
            }

            if (row == 0) {
                if (board[row][column] == Disc.YELLOW) {
                    countChip++;
                }
            }

            if (countChip == 4) {
                result = Disc.YELLOW;
                return result;
            }
        }

        // check reverse diagonal starting at column
        for (int i = board[board.length-1].length-1; i > 0; i--) {
            int row = board.length-1;
            int column = i;
            int countChip = 0;
            while (row > 0 && column > 0) {
                if (board[row][column] == Disc.YELLOW) {
                    countChip++;
                } else {
                    countChip = 0;
                }
                row--;
                column--;
            }

            if (column == 0) {
                if (board[row][column] == Disc.YELLOW) {
                    countChip++;
                }
            }

            if (countChip == 4) {
                result = Disc.YELLOW;
                return result;
            }
        }
        
        // check for player R
        // check horizontal
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length-3; j++) {
                if (board[i][j] == Disc.RED && board[i][j+1] == Disc.RED && board[i][j+2] == Disc.RED && board[i][j+3] == Disc.RED) {
                    result = Disc.RED;
                }
            }
        }
        // check vertical
        for (int i = 0; i < board.length-3; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == Disc.RED && board[i+1][j] == Disc.RED && board[i+2][j] == Disc.RED && board[i+3][j] == Disc.RED) {
                    result = Disc.RED;
                }
            }
        }
        // check diagonal starting at row
        for (int i = 0; i < board.length; i++) {
            int row = i;
            int column = 0;
            int countChip = 0;
            while (row > 0) {
                if (board[row][column] == Disc.RED) {
                    countChip++;
                } else {
                    countChip = 0;
                }
                row--;
                column++;
            }

            if (row == 0) {
                if (board[row][column] == Disc.RED) {
                    countChip++;
                }
            }

            if (countChip == 4) {
                result = Disc.RED;
                return result;
            }
        }
        // check diagonal starting at column
        for (int i = 0; i < board[board.length-1].length; i++) {
            int row = board.length-1;
            int column = i;
            int countChip = 0;
            while (row > 0 && column < board[board.length-1].length) {
                if (board[row][column] == Disc.RED) {
                    countChip++;
                } else {
                    countChip = 0;
                }
                row--;
                column++;
            }

            if (column == 0) {
                if (board[row][column] == Disc.RED) {
                    countChip++;
                }
            }

            if (countChip == 4) {
                result = Disc.RED;
                return result;
            }
        }

        // check reverse diagonal starting at last row
        for (int i = 0; i < board.length; i++) {
            int row = i;
            int column = board[0].length-1;
            int countChip = 0;
            while (row > 0) {
                if (board[row][column] == Disc.RED) {
                    countChip++;
                } else {
                    countChip = 0;
                }
                row--;
                column--;
            }

            if (row == 0) {
                if (board[row][column] == Disc.RED) {
                    countChip++;
                }
            }

            if (countChip == 4) {
                result = Disc.RED;
                return result;
            }
        }

        // check reverse diagonal starting at column
        for (int i = board[board.length-1].length-1; i > 0; i--) {
            int row = board.length-1;
            int column = i;
            int countChip = 0;
            while (row > 0 && column > 0) {
                if (board[row][column] == Disc.RED) {
                    countChip++;
                } else {
                    countChip = 0;
                }
                row--;
                column--;
            }

            if (column == 0) {
                if (board[row][column] == Disc.RED) {
                    countChip++;
                }
            }

            if (countChip == 4) {
                result = Disc.RED;
                return result;
            }
        }
        return result;
    }
    
    


    /**
     * toString returns a string representation of the board
     * @return the string representation of the board
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                Disc player = board[i][j];
                switch(player) {
                    case YELLOW:
                        if (j == 0) {
                            sb.append('|').append('Y');
                        } else if (j == 6) {
                            sb.append('|').append('Y').append('|');
                        } else {
                            sb.append('|').append('Y');
                        }
                        break;
                    case RED:
                        if (j == 0) {
                            sb.append('|').append('R');
                        } else if (j == 6) {
                            sb.append('|').append('R').append('|');
                        } else {
                            sb.append('|').append('R');
                        }
                        break;
                    default:
                        if (j == 0) {
                            sb.append('|').append(' ');
                        } else if (j == 6) {
                            sb.append('|').append(' ').append('|');
                        } else {
                            sb.append('|').append(' ');
                        }
                }
            }
            sb.append('\n');
        }
        return sb.toString();
    }

    /**
     * test and display game state changes
     *
     * @param args command line arguments (not used)
     */
    public static void main(String[] args) {
        Board b = new Board();

        // TODO (optional) put Board tests here.
    }
}
