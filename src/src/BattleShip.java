/**
 * Navjot Kamboj- 40240781 and Yatish Chutani -40266553
 * COMP 6481 (Programming and Problem Solving)
 * Assignment #1 (Part-II)
 * Due Date: 11th Feb, 2024
 *
 */
public class BattleShip {
    private char[][] board = new char[8][8];
    private int humanUserShips;
    private int computerShips;

    /**
     * Default constructor of BattleShip Class
     * Initializes default values to the variables declared above and
     * Creates the default board array with '_'
     */
    BattleShip(){
        humanUserShips = 0;
        computerShips =0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = '_';
            }
        }
    }

    /**
     * Setter method to place human ships on the board
     * Replaces '_' in board array with 's'
     * @param userShip Accepts coordinates of User Ship in Uppercase
     */
    public void setHumanUserShips(String userShip) {
        //Converting characters into corresponding ascii values
        board[((int) userShip.charAt(1)) - 49][((int) userShip.charAt(0)) - 65] = 's';
        humanUserShips++;
    }

    /**
     * Setter method to place human grenades on the board
     * Replaces '_' in board array with 'g'
     * @param userGrenade Accepts coordinates of User Grenades in Uppercase
     */
    public void setHumanUserGrenades(String userGrenade) {
        //Converting characters into corresponding ascii values
        board[((int) userGrenade.charAt(1)) - 49][((int) userGrenade.charAt(0)) - 65] = 'g';
    }

    /**
     * Method to verify if the coordinates entered by user are valid or not
     * @param coord Represents the coordinate entered by user
     * @return true if the coordinates entered are valid / if the chosen cordinate is already been used
     */
    public boolean isCoordinateValid(String coord) {
        /*Checks for Alphabet between A-H using their ASCII values 65 till 72 at Index 0 in the String &
        Checks for valid number between 1-8 at Index 1
         */
        if (((int) coord.charAt(0)) < 65 || ((int) coord.charAt(0)) > 72 ||
                Character.getNumericValue(coord.charAt(1)) < 1 || Character.getNumericValue(coord.charAt(1)) > 8 ) {
            System.out.println("Sorry, coordinates outside the grid. Try again.");
            return true;
            // Check for coordinate been already used by comparing with the default value
        }
        else if (board[(int) coord.charAt(1) - 49][(int) coord.charAt(0) - 65] != '_') {
            System.out.println("Sorry, coordinates already used. Try again.");
            return true;
        }
        return false;
    }

    /**
     * Setter method to place ships and grenades for computer randomly
     * at coordinates which are not used by human player
     * Replaces '_' in the board array with 'S' to represent ships and 'G' to represent grenades
     */
    public void setComputerShipsAndGrenades() {
        int c1 = (int) (Math.random() * 8);
        int c2 = (int) (Math.random() * 8);

        for (int i = 0; i < 6; i++) {
            while (board[c2][c1] != '_') {
                c1 = (int) (Math.random() * 8);
                c2 = (int) (Math.random() * 8);
            }
            board[c2][c1] = 'S';
            computerShips++;
        }
        for (int i = 0; i < 4; i++) {
            while (board[c2][c1] != '_') {
                c1 = (int) (Math.random() * 8);
                c2 = (int) (Math.random() * 8);
            }
            board[c2][c1] = 'G';
        }
    }

}
