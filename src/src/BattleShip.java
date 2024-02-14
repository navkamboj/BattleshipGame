/**
 * Navjot Kamboj- 40240781 and Yatish Chutani -40266553
 * COMP 6481 (Programming and Problem Solving)
 * Assignment #1 (Part-II)
 * Due Date: 14th Feb, 2024
 *
 */
public class BattleShip {
    private char[][] board = new char[8][8];
    private int humanUserShips;
    private int computerShips;
    private int humanUserTurnLoss;
    private int computerTurnLoss;
    private int counterTurn;
    private boolean hitGrenade;

    /**
     * Default constructor of BattleShip Class
     * Initializes default values to the private attributes declared above and
     * Creates the default board array with '_'
     */
    BattleShip(){
        hitGrenade = false;
        counterTurn = 0;
        humanUserShips = 0;
        computerShips =0;
        humanUserTurnLoss = 0;
        computerTurnLoss = 0;
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

    /**
     * This method checks if win condition is met, that is, when the humanUserShips or the computerShips
     * have all been sunk and the value becomes 0.
     *
     * @return false, whether every ship on either side has been sunk(game over);
     * true, if game is still ongoing
     */
    public boolean isRunning(){
        if(humanUserShips == 0){
            System.out.print("\nGood game, I win let's play again!\n");
            return false;
        } else if(computerShips == 0){
            System.out.print("\nYou Win!\n");
            return false;
        } else {
            return true;
        }
    }

    /**
     * This method is used to alternate between user and computer
     * using the counterTurn and a modulus, as the game progresses
     *
     * @return true, when it is the human user's turn; false, when it's computer's turn
     */
    public boolean gameTurn(){
        if(counterTurn % 2 == 0){
            return true;
        } else {
            return false;
        }
    }

    /**
     * This method is used to increment the value of counterTurn
     * and to verify if a grenade has been hit or not.
     *
     * The game continues normally if a grenade is not hit
     * and the turn counter is increased.
     *
     * The player who hit the grenade will set hitGrenade to false and the counter
     * will not increase giving the opponent an extra turn
     *
     */
    private void verifyTurn(){
        if(!hitGrenade){
            counterTurn++;
        } else {
            hitGrenade = false;
        }
    }

    /**
     * This method is used to pass user target coordinates into modifyBoard
     * as it is a private method.
     *
     * @param value coordinates entered by human player
     *
     */
    public void humanUserMove(String value) {
        modifyBoard(value);
    }

    /**
     * This method is uses random computer target coordinates using the random function
     * and prints it with the appropriate notation
     *
     * Pass coordinates into updateBoard
     */
    public void computerMove(){
        int a = (int) (Math.random() * 7) + 65;
        int b = (int) (Math.random() * 7) + 49;
        String value = Character.toString((char) a) + Character.toString((char) b);
        System.out.print(value + "\n");
        modifyBoard(value);
    }

    /**
     * A getter method to return humanUserTurnLoss
     *
     * @return number of player turns lost to grenades
     */
    public int getHumanUserTurnLoss(){
        return humanUserTurnLoss;
    }

    /**
     * A getter method to return computerTurnLoss
     *
     * @return number of computer turns lost to grenades
     */
    public int getComputerTurnLoss(){
        return computerTurnLoss;
    }

    /**
     * This method is used to trigger the turnLoss counter for
     * human user and computer when a grenade is hit.
     *
     */
    private void lostTurns(){
        if(gameTurn()){
            humanUserTurnLoss++;
        } else {
            computerTurnLoss++;
        }
    }

    /**
     * This method is used to print 8x8 board in its current state
     *
     * It prints corresponding character to show when element is hit
     * by user/computer using the letter codes from modifyBoard.
     *
     * This method also checks for any remaining
     * ships/grenades when the game is over in order print the location of
     * all elements on the board, and replaces any '*' with an '_'.
     *
     */
    public void printModifiedBoard(){
        System.out.println();
        for (int i = 0; i < board.length; i++){
            System.out.print("\t\t");
            for (int j = 0; j < board[i].length; j++){
                if (board[i][j] == 'h' || (board[i][j] == 's' && (humanUserShips == 0 || computerShips == 0))) {
                    System.out.print("s" + " ");
                } else if (board[i][j] == 'H' || (board[i][j] == 'S' && (humanUserShips == 0 || computerShips == 0))) {
                    System.out.print("S" + " ");
                } else if (board[i][j] == 'b' || (board[i][j] == 'g' && (humanUserShips == 0 || computerShips == 0))) {
                    System.out.print("g" + " ");
                } else if (board[i][j] == 'B' || (board[i][j] == 'G' && (humanUserShips == 0 || computerShips == 0))) {
                    System.out.print("G" + " ");
                } else if (board[i][j] == '*') {
                    if (computerShips == 0 || humanUserShips == 0) {
                        System.out.print("_ ");
                    } else {
                        System.out.print(board[i][j] + " ");
                    }
                } else {
                    System.out.print("_ ");
                }
            }
            System.out.println();
        }
    }

    /**
     * The method examines the board at the specified coordinates and adjusts it based on
     * the content found at that location which includes empty coordinates, a user or computer ship,
     * a user or computer grenade, or a previously targeted coordinate. When a content is found,
     * the board array will be updated with symbols representing the outcomes: '*', 'h/H', or 'b/B', respectively.
     *
     * When a ship is hit, the respective ship counter is decremented
     *
     * When a grenade is hit, turnsLost is called to track the number of turns lost due to grenades for each player.
     * It increments turnCounter and sets grenadeHit to true,
     * so that the next player has consecutive turns.
     *
     * When anything other than a grenade is hit, checkingTurn is called to
     * alternate turns between each player(unless a grenade is hit)
     *
     * @param value it is the target coordinate for where to update board array
     */
    private void modifyBoard(String value) {
        int a = (int) value.charAt(1) - 49;
        int b = (int) value.charAt(0) - 65;
        if(board[a][b] == '_'){
            System.out.print("nothing.");
            board[a][b] = '*';
            verifyTurn();
        } else if(board[a][b] == 's' || board[a][b] == 'S'){
            System.out.print("ship hit.");
            board[a][b] = ((board[a][b] == 's') ? 'h' : 'H');
            if (board[a][b] == 'h'){
                humanUserShips--;
            } else {
                computerShips--;
            }
            verifyTurn();
        } else if(board[a][b] == 'g' || board[a][b] == 'G'){
            System.out.print("boom! grenade.");
            board[a][b] = ((board[a][b] == 'g') ? 'b' : 'B');
            lostTurns();
            counterTurn++;
            hitGrenade = true;
        } else {
            System.out.print("position already called.");
            verifyTurn();
        }
        printModifiedBoard();
    }
}
